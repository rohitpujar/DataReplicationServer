Data Replication across Servers with Fault tolerance,High Availability and Incremental Scalability
--------------------------------------------------------------------------------------------------

This is a Server version of Data replication system.

* Any number of servers can be initiated - Config file should contain the port numbers and host names. 
* Network partition simulated - Partition can be configured through an external file.
* Node zero of server side is considered a special node, and it is the one that can communicate to other servers to indicate   them to create a network partition. Other than this server node zero is no different than other server nodes.


SERVER DESIGN


1.	For each object, we consider there is a server which acts as a primary server. This primary server is determined by the     value that the hash function generates on the object. The rest two servers act as secondary servers.
2.	This distinction was important, since it facilitates us to maintain total ordering among the servers for data               replication. 
3.	The client writes first to the primary servers followed by the secondary servers.
4.	If the primary server is down or is in other part of partition and can’t be accessed, the secondary server succeeding       the primary server will act as a pseudo primary server.
5.	There is no difference between how the primary and secondary servers behave, they all have the same functionality, since     for each different object there is a different primary server. Hence there is no notion that one server is handling too     many operations.
6.	This design helped us design the system consistently and to design the whole system in conformity to this decision,         which resulted in overall stable system.


Connections to the nodes in System

1.	The connections are the standard sockets. The information for this configuration is defined in an external file. The        file can be altered, and the system conforms to such changes in the topology.
2.	Network partition configuration is also read from an external file, and the connections get configured accordingly,         creating a partition between themselves. 
