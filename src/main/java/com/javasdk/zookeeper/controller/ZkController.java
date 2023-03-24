package com.javasdk.zookeeper.controller;

import java.io.UnsupportedEncodingException;

import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasdk.zookeeper.service.ZKManager;

@RestController
@RequestMapping("/api")
public class ZkController {
	
	@Autowired
	private ZKManager zk;
	
	@PostMapping("/{value}")
	public String createNode(@PathVariable String value) {
		try {
			String s ="Mydata";
			zk.create("/"+value, s.getBytes());
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Node /"+value+"created";
	}
	
	@GetMapping("/{value}")
	public String getNode(@PathVariable String value) {
		Object a=null;
		try {
			try {
				a = zk.getZNodeData("/"+value, false);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a.toString() ;
	}
	
	
	@PutMapping("/{value}")
	public String updateNode(@PathVariable String value) {
		try {
			String s ="Mydata123";
			zk.update("/"+value, s.getBytes());
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Node /"+value+"created";
	}
}
