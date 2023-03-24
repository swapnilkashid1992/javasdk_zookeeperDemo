package com.javasdk.zookeeper.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import com.javasdk.zookeeper.connection.ZKConnection;

@Service
public class ZKManagerImpl implements ZKManager {

	private static ZooKeeper zkeeper;
	private static ZKConnection zkConnection;


	public void closeConnection() throws InterruptedException {
		zkConnection.close();
	}

	public void initialize() throws IOException, InterruptedException {
		zkConnection = new ZKConnection();
		zkeeper = zkConnection.connect("127.0.0.1:2181");
	}

	@Override
	public void create(String path, byte[] data) throws KeeperException, InterruptedException {
		try {
			initialize();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		zkeeper.create(
		          path, 
		          data, 
		          ZooDefs.Ids.OPEN_ACL_UNSAFE, 
		          CreateMode.PERSISTENT);
		
		closeConnection();
	}

	@Override
	public Object getZNodeData(String path, boolean watchFlag) throws UnsupportedEncodingException, KeeperException, InterruptedException {
		try {
			initialize();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		byte[] b = null;
	        b = zkeeper.getData(path, null, null);
	        closeConnection();
	        return new String(b, "UTF-8");
	        
	}

	@Override
	public void update(String path, byte[] data) throws KeeperException, InterruptedException {
		try {
			initialize();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		int version = zkeeper.exists(path, true).getVersion();
        zkeeper.setData(path, data, version);
		
	}

	


}
