/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internship_kernel_approximation;

/**
 *
 * @author sarwan ali
 */
import java.io.IOException;
import java.math.BigDecimal;

public class Internship_kernel_approximation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
		
        // default values to be used as defined in original paper
	double sigma = 0.0; 
        int b = 0;

        int[] Trial = new int[]{1}; //{1,2,3}; //4,6,8
        for(int ind_Trial =0;ind_Trial<Trial.length; ind_Trial++){ 

            // number of sequences in data file is the total sequence in corresponding station id based data file
            // possible values are [14248,1559,480,18728,1041,944]
            int number_of_sequences = 944;
            
            // alphabet size is predefined from the data. See the project report the get an idea of alphabet size for each station id based data file
            // possible values for top location ids are [138,30,19,135,26,18]
            int alphabet_size_val = 18; 
            // output path where the kernel matrix will be stored
            String output_path = "C:\\\\Users\\\\username\\\\Desktop\\";
            // file name of the resultant kernel matrix
            String tmp_name = "seq_data_intern_" + number_of_sequences;

            //input filename along with the file path of the original sequence data file
            String file_name = "annon_original_seq_" +number_of_sequences + ".csv";
            String data_path = "C:\\Users\\username\\Desktop\\" + file_name;
            
            // below we select value of k for kmers. We use k=3 which give us optimal results overall
            int[] val_for_k = new int[]{3};
            // below we select value of m for kmers. We use m=0 which give us optimal results overall
            int[] val_for_m = new int[]{0};

            
            for(int ind_k =0;ind_k<val_for_k.length; ind_k++){ 
                for(int ind_m =0;ind_m<val_for_m.length; ind_m++){
                    System.out.println("ind_k =>" + val_for_k[ind_k] + ", ind_m =>" + val_for_m[ind_m]);
                    int k = val_for_k[ind_k], m = val_for_m[ind_m], num_seqs = number_of_sequences, 
                            min2mkminus1 = Math.min(2*m, k - 1), min2mk = Math.min(2*m, k);
                    BigDecimal alphabet_size = new BigDecimal(alphabet_size_val);
                    String dataFile = data_path ; //args[0];

                    OperationsForKernelComputation2 kop = new OperationsForKernelComputation2(dataFile, k, m, min2mkminus1, min2mk, alphabet_size, b, sigma, num_seqs);
                    System.out.println("First Done!!");
                    kop.pairsOfKmersAtDistanceMin2mk();
                    kop.writeToFile(output_path + tmp_name + "_" + "_dataset_Alligned_with_variants_Kernel_k"+k+"_m"+m+ "_Alphabet_Size" + alphabet_size_val + "_trial_" + Trial[ind_Trial] + ".txt");
                }
            }
        }
    }
    
}
