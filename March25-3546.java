//https://leetcode.com/problems/equal-sum-grid-partition-i/?envType=daily-question&envId=2026-03-25

class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        boolean res=prefix_sum(m,n,grid,1) || prefix_sum(n,m,grid,0);
        return res;
    }
    public boolean prefix_sum(int r, int c, int[][]grid,int flag){
        long total_sum=0;
         long prefix[]=new long[r];
        for(int i=0;i<r;i++){
            long sum=0;
            for(int j=0;j<c;j++){
                if(flag==1)
                sum+=grid[i][j];
                else sum+=grid[j][i];
            }
            prefix[i]=sum;
            total_sum+=sum;
        }
        long cur_sum=0;
        for(int i=0;i<r;i++){
            cur_sum+=prefix[i];
            if(cur_sum==total_sum-cur_sum)return true;
        }
        return false;
    }
}