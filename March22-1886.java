//https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/?envType=daily-question&envId=2026-03-22

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        boolean res=compare(mat,target);
        for(int i=0;i<3;i++){
            mat=rotate(mat);
            res=res||compare(mat,target);
        }
        return res;
    }
    public int[][] rotate(int[][]mat){
        int n=mat.length;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int temp=mat[i][j];
                mat[i][j]=mat[j][i];
                mat[j][i]=temp;
            }
        }
        for(int col=0;col<n/2;col++){
            for(int row=0;row<n;row++){
                int col2=n-1-col;
                int temp=mat[row][col];
                mat[row][col]=mat[row][col2];
                mat[row][col2]=temp;
            }
        }
        return mat;
    }
    public boolean compare(int [][]mat, int [][]target){
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                if(mat[i][j]!=target[i][j])return false;
            }
        }
        return true;
    }
}