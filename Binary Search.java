	int lo=0, hi=n, ans=0, mid;
            while(lo <= hi){
                mid = (lo+hi)>>1;
                if(check(mid, arr)) { lo = mid + 1; ans = mid; }
                else { hi = mid -1; }
            }
 
    System.out.println(ans);