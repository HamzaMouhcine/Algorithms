Integer bound(final List<Integer> A,int B,boolean searchFirst){
    int n = A.size();
    int low = 0;
    int high = n-1;
    int res = -1;   //if element not found
    int mid ;
    while(low<=high){
        mid = low+(high-low)/2;
        if(A.get(mid)==B){
            res=mid;
            if(searchFirst){high=mid-1;}    //to find first , go left
            else{low=mid+1;}                // to find last, go right
        }
        else if(B>A.get(mid)){low=mid+1;}
        else{high=mid-1;}
    }
    return res;
}