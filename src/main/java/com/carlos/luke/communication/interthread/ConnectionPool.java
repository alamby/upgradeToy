package com.carlos.luke.communication.interthread;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
  private List<Connection> connections = createConnections();

  private List<Connection> createConnections() {
    List<Connection> conns = new ArrayList<Connection>(5);
    for (int i = 0; i < 5; i++) {
      //... add a Connection to conns
    }
    return conns;
  }
  
  public Connection getConnection() throws InterruptedException {
      synchronized (connections) {
        while (connections.isEmpty()) {
          connections.wait();
        }
        return connections.remove(0);
      }
    }
  
  public void returnConnection(Connection conn) {
      synchronized (connections) {
        connections.add(conn);
        connections.notify();
      }
    }
}