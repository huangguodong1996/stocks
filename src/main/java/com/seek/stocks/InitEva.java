package com.seek.stocks;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InitEva implements ApplicationRunner {


    @Autowired
    public CuratorFramework curatorFramework;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        //启动连接
        //curatorFramework.start();

        //4、建立一个Cache缓存 ,第三个参数是 dataIsCompressed 是否进行数据压缩  ,需要配置为true
        //如果第三个参数设置为 false,则不接受节点变更后的数据
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework,"/curator",true);

        //5、设定监听的模式 ,异步初始化，初始化完成触发事件 PathChildrenCacheEvent.Type.INITIALIZED
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);


        //创建监听器
        cache.getListenable().addListener(new PathChildrenCacheListener() {

            public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
                // TODO Auto-generated method stub
                ChildData data = event.getData();
                if(data != null){
                    System.out.println("路径\t"+data.getPath());
                    System.out.println("更改数据\t"+new String(data.getData()));
                    System.out.println("节点状态\t"+data.getStat());
                    System.out.println(event.getType());
                }
                switch (event.getType()) {
                    //初始化会触发这个事件
                    case INITIALIZED:
                        System.out.println("子类缓存系统初始化完成");
                        break;
                    case CHILD_ADDED:
                        System.out.println("添加子节点");
                        break;
                    case CHILD_UPDATED:

                        System.out.println("更新子节点");
                        break;
                    case CHILD_REMOVED:
                        System.out.println("删除子节点");
                        break;
                    default:
                        break;
                }

                System.out.println("----------------------------------");
            }
        });

        //判断节点是否存在，然后删除掉
        Stat stat = curatorFramework.checkExists().forPath("/curator/nodecache2");
        if(stat != null){
            Thread.sleep(1000);
            curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator/nodecache2");
        }

        //创建节点
        Thread.sleep(1000);
        curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/curator/nodecache2","nodecache  test".getBytes());

        //数据修改
        Thread.sleep(1000);
        curatorFramework.setData().forPath("/curator/nodecache2","update".getBytes());

        //获取节点数据
        Thread.sleep(1000);
        byte [] data = curatorFramework.getData().forPath("/curator/nodecache2");
        System.out.println(new String(data));


        //删除节点
        Thread.sleep(1000);
        //curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator/nodecache2");
    }
}
