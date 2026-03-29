//https://leetcode.com/problems/check-if-strings-can-be-made-equal-with-operations-i/description/?envType=daily-question&envId=2026-03-29


class Solution {
    public boolean canBeEqual(String s1, String s2) {
        StringBuilder sb1=new StringBuilder(s1);
        StringBuilder sb2=new StringBuilder(s2);
        for(int i=0;i<4;i++){
            if(s1.charAt(i)==s2.charAt(i))continue;
            if(s1.charAt(i)!=s2.charAt(i)){
                if(i+2<4 && (s1.charAt(i+2)!=s2.charAt(i) && s2.charAt(i+2)!=s1.charAt(i) ))return false;
                else if((s1.charAt(i+2)!=s2.charAt(i)){
                        stemp c=sb1.charAt(i);
                        sb1.setCharAt(i,sb1.charAt(i+2));
                } s2.charAt(i+2)!=s1.charAt(i) ))
            }
            
        }
        return true;
    }
}