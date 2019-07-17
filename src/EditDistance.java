import java.util.*;

public class EditDistance implements EditDistanceInterface {

    int c_i, c_d, c_r;
    static int MAX = Integer.MAX_VALUE;
    static int UNDEF = -1;

    // constructor that initializes the costs of the different operations
    public EditDistance (int c_i, int c_d, int c_r) {
        this.c_i = c_i;
        this.c_d = c_d;
        this.c_r = c_r;
    }

    com- putes and returns the table D
    public int[][] getEditDistanceDP(String s1, String s2) {
        // initialization of D
    	int[][] D = new int[s1.length()+1][s2.length()+1];
        for (int i=0; i<D.length; i++)
        	D[i][0] = i*this.c_d;
        for (int j=0; j<D[0].length; j++)
        	D[0][j] = j*this.c_i;

        // recursion to compute D
        for (int i=1; i<D.length; i++) {
    		for (int j=1; j<D[0].length; j++) {
   				if (s1.charAt(i-1) == s2.charAt(j-1))   // if s1[i-1] = s2[j-1]
   					D[i][j] = Math.min(D[i-1][j-1], Math.min(D[i-1][j]+this.c_d,D[i][j-1]+this.c_i));
    			else
    				D[i][j] = Math.min(D[i-1][j-1]+this.c_r, Math.min(D[i-1][j]+this.c_d,D[i][j-1]+this.c_i));
    		}
        }
        return D;
    }


    // write a new (separate, private) version of getEditDistanceDP that maintains
    //both the array di,j containing the edit distance and an array opi,j that contains
    //the last (optimal) operation to perform when transforming A[0..i − 1] to B[0.. j − 1]
    public List<String> getMinimalEditSequence(String s1, String s2) {
    	// initialization of D and op
    	LinkedList<String> LS = new LinkedList<String>();
    	int n = s1.length();
    	int m = s2.length();
    	int[][] D = new int[n+1][m+1];
    	int[][] op = new int[n+1][m+1];
    	for (int i=0; i<n; i++) {
    		for (int j=0; j<m; j++)
    			D[i][j] = EditDistance.MAX;
    	}
        for (int i=0; i<n+1; i++) {
        		D[i][0] = i*this.c_d;
        		op[i][0] = 2;       // op[i][j] = 2 if we delete a character
        }
        for (int j=0; j<m+1; j++) {
        		D[0][j] = j*this.c_i;
        		op[0][j] = 3;       // op[i][j] = 3 if we insert a character
        }
        op[0][0] = 0;       // op[i][j] = 0 if we do anything


        // recursion to compute D
        for (int i=1; i<D.length; i++) {
    		for (int j=1; j<D[0].length; j++) {
    			if (s1.charAt(i-1) == s2.charAt(j-1)) {   // if s1[i-1] = s2[j-1]
   					D[i][j] = Math.min(D[i-1][j-1], Math.min(D[i-1][j]+this.c_d,D[i][j-1]+this.c_i));
   					if (D[i][j] == D[i-1][j-1])
   	   					op[i][j] = 0; //"";
    			}
   				else {
   					D[i][j] = Math.min(D[i-1][j-1]+this.c_r, Math.min(D[i-1][j]+this.c_d,D[i][j-1]+this.c_i));
    				if (D[i][j] == D[i-1][j-1]+this.c_r)
        				op[i][j] = 1;      // op[i][j] = 1 if we replace a character
   				}

    			// computation of op
    			if (D[i][j] == D[i-1][j]+this.c_d)
    				op[i][j] = 2;        // op[i][j] = 2 if we delete a character
    			else if (D[i][j] == D[i][j-1]+this.c_i)
   					op[i][j] = 3;        // op[i][j] = 3 if we insert a character
   			}
        }

        // reconstruction of the edit distance
        int i = n;
        int j = m;
        while(!(i == 0 && j == 0)) {   // we go from (n,m) to (0,0)
        	switch (op[i][j]) {
        	case 1 :
        		LS.add("replace(" + (i-1) + "," + s2.charAt(j-1) + ")");
        		i--;
        		j--;
        		break;
        	case 2 :
        		LS.add("delete(" + (i-1) + ")");
        		i--;
        		break;
        	case 3 :
        		LS.add("insert(" + (i) + "," + s2.charAt(j-1) + ")");
        		j--;
        		break;
        	default :
        		i--;
        		j--;
        		break;
        	}
        }
        return LS;
    }

}
