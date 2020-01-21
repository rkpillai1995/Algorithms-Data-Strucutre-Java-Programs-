/*
 * Planters.java
 *
 * Version :   1.0
 */

import java.util.Scanner;
/**
 * This program prints YES if all plants can be replanted and no otherwise
 *
 * @author      Rajkumar Lenin Pillai
 *
 */
public class Planters {
    /**
     * This method performs sorting of array using mergesort

     * @param a  int array to be sorted
     * @param n  int size of the array to be sorted
     * @return a  sorted array
     */


        public static   int[] mergesort(int a[],int n){

            int temp[]=new int[a.length];
            int i,j,k,lb1,lb2,ub1,ub2=0,size;
            size=1;

            while (size<n) {

                lb1 = 0;
                k = 0;
                while (lb1 + size < n) {
                    lb2 = lb1 + size;
                    ub1 = lb2 - 1;
                    if (ub1 + size < n) {
                        ub2 = ub1 + size;
                    }
                    else {
                        ub2 = n - 1;
                    }

                    i = lb1;
                    j = lb2;


                    while (i <= ub1 && j <= ub2) {
                        if (a[i] < a[j]) {
                            temp[k++] = a[i++];
                        }
                        else {
                            temp[k++] = a[j++];
                        }
                    }

                    while (i <= ub1) {
                        temp[k++] = a[i++];
                    }

                    while (j <= ub2) {
                        temp[k++] = a[j++];
                    }

                    lb1 = ub2 + 1;

                }
                for (i = 0; i <= ub2; i++) {
                    a[i] = temp[i];
                }

                size = size * 2;
            }

            return a;                                                   // return the sorted array
        }



    /**
     * Implementing the binary search to find a planter of larger siz
     * @param data the array in which binary search is to be performed
     * @param val  the size whose greater size is to be found
     * @param left  the left pointer
     * @param right the right pointer
     * @param track  Specifies whether it is one of the adjecent planters or a extra planter
     * @return left the larger size planter
     */
    public static int binarysearcRec( int data[],int val,int left,int right,int track[]) {

            int midindex;
            while (left <= right) {
                midindex = (left + right) / 2;

                /**if(data[midindex]==val){
                 return data[midindex];
                 }
                 else**/

                if (val < data[midindex]) {
                    right = midindex - 1;
                } else {
                    left = midindex + 1;
                }

            }

            if(left>=data.length){
                return -1;
            }


            if (track[left]==0){
                return left;
            }
            else {
                return binarysearcRec(data,val,left+1,data.length-1,track);     // Recursive call
            }
        }


    /**
     * The binary search helper is called which check whether adjacent planter or extra planter is to be searched
     * @param data the array in which binary search is to be performed
     * @param val  the size whose greater size is to be found
     * @param planttrack The array showing availability planter
     * @param extratrack The array showing availability extraplanter
     * @param type       Mentions whether it is a planter or a extra planter
     * @param plantposition the current size of planter
     * @return result the larger size planter
     */
        public static int binarysearchhelper(int data[],int val,int planttrack[],int extratrack[],String type,int plantposition)
        {

            if (type == "plant") {
                //System.out.println(Arrays.toString(planttrack));
                int result = binarysearcRec(data, val, 0, data.length - 1,planttrack);
                return result;
            }
            else {
                int result = binarysearcRec(data, val, 0, data.length - 1,extratrack);
                return result;
            }
        }
        public static void main(String[] args) {

            Scanner sc=new Scanner(System.in);
            int p=Integer.parseInt(sc.next());
            int r=Integer.parseInt(sc.next());
            int plant[]=new int[p];
            int extra[]=new int[r];


            int count=p; // To keep track whether all plants are replanted
            int n=p+r;


            // Initializing the plant and extra track to keep track of the availability of planter
            int planttrack[]=new int[plant.length];
            for(int i=0;i<plant.length;i++){
                planttrack[i]=1;
            }
            int extratrack[]=new int[extra.length];
            for(int i=0;i<extra.length;i++){
                extratrack[i]=0;
            }





            for(int i=0;i<plant.length;i++){
                plant[i]=Integer.parseInt(sc.next());
            }

            for(int i=0;i<extra.length;i++){
                extra[i]=Integer.parseInt(sc.next());
            }

            // Sorting the extraPlanters and planters in order of their size
            plant=mergesort(plant,plant.length);
            extra=mergesort(extra,extra.length);

            // String which helps in restricting the searcg to planters or extraplanters
            String x="plant";
            String y="extra";


            // Searching for the next larger planter available
            for(int i=plant.length-1;i>-1;i--){

                // calling binary search to look for a larger planter
                int position=binarysearchhelper(plant,plant[i],planttrack,extratrack,x,i);

                //Checking If planter is empty
                if (position!=-1&&(planttrack[position]!=1) ){
                    planttrack[position]=1;
                    planttrack[i]=0;
                    count--;

                }

                else{
                    // calling binary search to look for a larger extraplanter

                    int pos = binarysearchhelper(extra, plant[i] ,planttrack,extratrack,y,i);

                    //Checking If planter is empty

                    if (pos != -1 && (extratrack[pos] != 1)) {
                        extratrack[pos] = 1;
                        planttrack[i] = 0;
                        count--;

                    }


                }




            }
            // Checking if all plants are replanted
            if (count==0){
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }

        }

    }





