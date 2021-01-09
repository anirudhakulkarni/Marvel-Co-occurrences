run:
	javac assignment4.java
	java assignment4 nodes.csv edges.csv
avg:
	javac assignment4.java
	java assignment4 nodes.csv edges.csv average
rank:
	javac assignment4.java
	java assignment4 nodes.csv edges.csv rank
dfs:
	javac assignment4.java
	java assignment4 nodes.csv edges.csv independent_storylines_dfs
clean:
	rm *.class