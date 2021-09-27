<h1>Where did I put my keys?</h1> <br/>
 Initial approach was to take a undirected graph implementation, but the approach on how we could insert the values in a graph was a challenge.
 Instead I decided to go for a Binary Tree implementation with recursion. The elements are inserted in the left by default if the node is null and the tree is search recursively to see if any elements exists and if not, then insert the new element based on the relationship it has with its previous node in the config file.
 
PrintPath algorithm from 2 nodes involved <br/>
•	first finding if the 2 node existed <br/>
•	finding the lca(Least common ancestor) among the 2 nodes <br/>
•	finding the path from one node to lca <br/>
•	finding the path from destination to lca<br/>
•	combining the both paths to get the complete path from source to destination
