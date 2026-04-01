//https://leetcode.com/problems/robot-collisions/?envType=daily-question&envId=2026-04-01

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n=positions.length;
        int [][]arr=new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0]=i;
            arr[i][1]=positions[i];
        }
        List<Integer>res=new ArrayList<>();
        Arrays.sort(arr,(a,b)->a[1]-b[1]);
        Stack<Integer>st=new Stack<>();
        for(int i=0;i<n;i++){
            int j=arr[i][0];
            char c=directions.charAt(j);
            if( c=='L'){
                if(st.isEmpty())res.add(j);
                else{
                    while(!st.isEmpty() && healths[st.peek()]<healths[j]){
                        st.pop();
                        healths[j]--;
                    }
                    if(st.isEmpty())res.add(j);
                    if(!st.isEmpty()){
                        if(healths[st.peek()]==healths[j])st.pop();
                        else healths[st.peek()]--;
                    }
                }
            }
            else {
                st.push(j);
            }
        }
        while(!st.isEmpty()){
            res.add(st.pop());
        }
        Collections.sort(res);
        for(int i=0;i<res.size();i++){
            int idx=res.get(i);
            res.set(i,healths[idx]);
        }
        return res;
    }
}