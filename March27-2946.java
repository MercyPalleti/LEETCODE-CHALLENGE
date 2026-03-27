//https://leetcode.com/problems/matrix-similarity-after-cyclic-shifts/?envType=daily-question&envId=2026-03-27

class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int n=mat[0].length;
        int m=mat.length;
        k=k%n;
        int res[][]=new int[m][n];
        //COPY MAT[][]-->RES[][]
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                res[i][j]=mat[i][j];
            }
        }
        
        if(k==0)return true;

        for(int i=0;i<m;i++){
            if(i%2==0){
                f(k,res,i);
                if(!compare(mat,res,i))return false;
            }
            else {
                f(n-k,res,i);
                if(!compare(mat,res,i))return false;
            }
        }
        return true;
    }
    public void f(int shiftBy,int[][]res,int row){
        reverse(0,shiftBy-1,row,res);
        reverse(shiftBy, res[0].length-1, row,res);
        reverse(0,res[0].length-1,row,res);
    }
    public void reverse(int start,int end, int row,int[][]res){
        while(start<end){
            int temp=res[row][start];
            res[row][start]=res[row][end];
            res[row][end]=temp;
            start++;
            end--;
        }
    }
    public boolean compare(int[][]mat, int[][]res, int row){
        for(int i=0;i<mat[0].length;i++){
            if(mat[row][i]!=res[row][i])return false;
        }
        return true;
    }

}