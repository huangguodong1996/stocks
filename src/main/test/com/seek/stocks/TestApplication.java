package com.seek.stocks;

import com.seek.stocks.autoconfigure.StartServiceAutoConfigure;
import com.seek.stocks.service.ZookeeperService;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

    Logger log = LoggerFactory.getLogger(TestApplication.class);

    @Autowired
    public ZookeeperService zookeeperService;


    /**
     * 测试创建节点
     */
    @Test
    public void testCreate(){
        System.out.println(zookeeperService.createPersistentNode("/curator/test_1","测试数据"));;
        System.out.println(zookeeperService.createEphemeralNode("/curator/test_2","测试临时节点"));
        System.out.println(zookeeperService.createSequentialEphemeralNode("/curator/test_3","测试临时节点"));

    }

    /**
     * 测试判断节点是否存在
     */
    @Test
    public void testCheckExists(){
        System.out.println(zookeeperService.checkExists("/curator/test_1"));
        System.out.println(zookeeperService.checkExists("/curator/test_xxxxx"));
    }

    /**
     * 测试获取某个Zookeeper节点的所有子节点
     */
    @Test
    public void testGetChildren(){
        List<String> list = zookeeperService.getChildren("/curator");
        list.forEach(System.out::println);
    }

    /**
     * 测试设置和获取值
     */
    @Test
    public void testSetAndGet(){
        //重新赋值
        zookeeperService.setData("/curator/test_1","新的测试数据");
        //获取值
        System.out.println(zookeeperService.getData("/curator/test_1"));
    }

    /**
     * 测试普通删除和级联删除
     */
    @Test
    public void testDelete(){
        zookeeperService.delete("/curator/test_1");
        //遍历
        testGetChildren();

        zookeeperService.deleteChildrenIfNeeded("/curator");
        //再次遍历
        testGetChildren();
    }

    /**
     * 测试节点监听器
     */
    @Test
    public void testNodeCacheListener() throws Exception {
        NodeCache nodeCache = zookeeperService.registerNodeCacheListener("/curator/test_nodeCache");

        //节点不存在，set（不触发监听）
        zookeeperService.setData("/curator/test_nodeCache","nodeCache_value_1");
        //节点不存在，create（触发监听）
        zookeeperService.createPersistentNode("/curator/test_nodeCache","nodeCache_value_2");
        //节点存在，set（触发监听）
        zookeeperService.setData("/curator/test_nodeCache","nodeCache_value_3");
        //节点存在，delete（触发监听）
        zookeeperService.delete("/curator/test_nodeCache");

        Thread.sleep(20000);

        //关闭NodeCache
        nodeCache.close();
    }

    /**
     * 测试子目录监听器
     */
    @Test
    public void testPathChildListener() throws Exception {
        PathChildrenCache pathChildrenCache = zookeeperService.registerPathChildListener("/curator", (client, event) -> {
            ChildData childData = event.getData();

            if(childData != null){
                System.out.println("Path: " + childData.getPath());
                System.out.println("Stat:" + childData.getStat());
                System.out.println("Data: "+ new String(childData.getData()));
            }

            switch (event.getType()){
                case CHILD_ADDED:
                    System.out.println("正在新增子节点：" + childData.getPath());
                    //获取子节点
                    List<String> list = zookeeperService.getChildren("/curator");
                    list.forEach(System.out::println);

                    break;
                case CHILD_UPDATED:
                    System.out.println("正在更新子节点："  + childData.getPath());
                    break;
                case CHILD_REMOVED:
                    System.out.println("子节点被删除");
                    break;
                case CONNECTION_LOST:
                    System.out.println("连接丢失");
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println("连接被挂起");
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("恢复连接");
                    break;
            }

        });

        //子节点不存在，set（不触发监听）
        zookeeperService.setData("/curator/test_pathChildrenCache","pathChildrenCache_value_1");
        //子节点不存在，create（触发监听）
        zookeeperService.createPersistentNode("/curator/test_pathChildrenCache","pathChildrenCache_value_2");
        Thread.sleep(2000);

        //子节点存在，set（触发监听）
        zookeeperService.setData("/curator/test_pathChildrenCache","pathChildrenCache_value_3");
        //子节点存在，create子节点的子节点（不触发监听）
        zookeeperService.createPersistentNode("/curator/test_pathChildrenCache/aaaa","child_value_4");
        //子节点存在，delete（触发监听）
        zookeeperService.deleteChildrenIfNeeded("/curator/test_pathChildrenCache");

        Thread.sleep(20000);

        //关闭PathChildrenCache
        pathChildrenCache.close();
    }

    /**
     * 测试目录监听器
     */
    @Test
    public void testTreeCacheListener() throws Exception {
        TreeCache treeCache = zookeeperService.registerTreeCacheListener("/curator", 2, (client, event) -> {
            ChildData childData = event.getData();

            if(childData != null){
                System.out.println("Path: " + childData.getPath());
                System.out.println("Stat:" + childData.getStat());
                System.out.println("Data: "+ new String(childData.getData()));
            }

            switch (event.getType()){
                case NODE_ADDED:
                    System.out.println("正在新增子节点：" + childData.getPath());
                    //获取子节点
                    List<String> list = zookeeperService.getChildren("/curator");
                    list.forEach(System.out::println);

                    break;
                case NODE_UPDATED:
                    System.out.println("正在更新节点："  + childData.getPath());
                    break;
                case NODE_REMOVED:
                    System.out.println("节点被删除：" + childData.getPath());
                    break;
                case CONNECTION_LOST:
                    System.out.println("连接丢失");
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println("连接被挂起");
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("恢复连接");
                    break;
            }

        });

        //子节点不存在，set（不触发监听）
        zookeeperService.setData("/curator/test_pathChildrenCache","pathChildrenCache_value_1");
        //子节点不存在，create（触发监听）
        zookeeperService.createPersistentNode("/curator/test_pathChildrenCache","pathChildrenCache_value_2");
        Thread.sleep(2000);

        //子节点存在，set（触发监听）
        zookeeperService.setData("/curator/test_pathChildrenCache","pathChildrenCache_value_3");
        //子节点存在，create子节点的子节点（触发监听）
        zookeeperService.createPersistentNode("/curator/test_pathChildrenCache/aaaa","child_value_4");
        //子节点存在，delete（触发监听）
        zookeeperService.deleteChildrenIfNeeded("/curator/test_pathChildrenCache");

        Thread.sleep(5000);


        //set监听的根节点（触发监听）
        zookeeperService.setData("/curator","curator_value_aaa");
        Thread.sleep(2000);

        //delete监听的根节点（触发监听）
        zookeeperService.deleteChildrenIfNeeded("/curator");

        //关闭TreeCache
        treeCache.close();
    }

}
