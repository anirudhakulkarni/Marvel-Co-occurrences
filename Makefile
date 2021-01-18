run:
	javac A4_2019CS50421V1.java
	java A4_2019CS50421V1 nodes.csv edges.csv
avg:
	javac A4_2019CS50421V1.java
	java A4_2019CS50421V1 nodes.csv edges.csv average
rank:
	javac A4_2019CS50421V1.java
	java A4_2019CS50421V1 nodes.csv edges.csv rank
dfs:
	javac A4_2019CS50421V1.java
	java A4_2019CS50421V1 nodes.csv edges.csv independent_storylines_dfs
clean:
	rm *.class