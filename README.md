# EditDitstanceBetweenStrings
Solves the Edit Distance problem for strings in Java


The edit sequence on the left uses two replace operations and hence costs 2Cr. The sequence on the right uses two insert and two delete operations and hence costs 2Cd + 2Ci. Depending on the relative costs, one of these sequences will be more efficient than the other. For example, if Ci = 3, Cd = 2, Cr = 1, then the sequence on the left is more efficient (cost = 2). However, if Ci = 3, Cd = 2, Cr = 6, then the sequence on the right is better (cost = 10).
To compute the optimal edit distance between two strings, we will use the following recurrence. Let di,j be the optimal edit distance between A[0..i−1] = a0...ai−1 and B[0..j−1] = b0 ...bj−1. In particular, note that d0,j is the number of edits to go from the empty string to B[0.. j − 1]; and di,0 is the number of edits to go from A[0..i − 1] to the empty string. The value of di,j can then be defined recursively.

The optimal edit distance between A and B can then be computed as dm,n. If we naively compute this value using the above recursive rules, we would perform exponential com- putation, because we would end up recomputing the same values di, j many times. Instead, we will use dynamic programming to compute dm,n in O(mn) time, using a table of size (m+1,n+1) to store di,j for all 0 ≤ i ≤ m, 0 ≤ j ≤ n.
