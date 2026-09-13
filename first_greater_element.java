import java.util.*;
public class FGE {
    static int tree[];
    static void build(int i,int st,int end,int arr[]) {
        if (st==end) {
            tree[i] =arr[st];
            return;
        }
        int mid =(st+end)/2;
        build(2*i+1, st,mid,arr);
        build(2*i+2, mid+1,end,arr);
        tree[i] =Math.max(tree[2*i+ 1],tree[2*i+2]);
    }
    static void update(int i,int st,int end,int arr[], int x, int val) {
        if (st ==end) {
            arr[x] =val;
            tree[i]=val;
            return;
        }
        int mid = (st+end)/2;
        if (x <=mid)
            update(2*i+1, st,mid,arr, x,val);
        else
            update(2*i+2,mid+1,end,arr,x,val);
        tree[i] = Math.max(tree[2*i+1],tree[2*i+2]);
    }

    static int query(int index, int st,int end, int l,int r,int x) {
        if (l>end || r<st) return -1;
        if (tree[index]<x) return -1;
        if (st==end) return st;
        int mid=(st + end)/2;
        int ans=query(2*index+1,st,mid,l,r,x);

        if (ans!=-1) return ans;
        return query(2*index+2,mid+1,end,l,r,x);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        tree = new int[4*n];
        for (int i=0; i<n; i++) arr[i]=sc.nextInt();
        build(0,0,n-1, arr);
        int Q =sc.nextInt();
        while (Q-->0) {
            int type=sc.nextInt();

            if (type==1) {
                int index=sc.nextInt();
                int val=sc.nextInt();
                update(0,0, n-1,arr,index,val);
            } else {
                int l=sc.nextInt();
                int r=sc.nextInt();
                int x=sc.nextInt();
                System.out.println(query(0,0,n-1,l,r,x));
            }
        }
    }
}
