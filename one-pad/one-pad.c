#include <stdio.h>

#define KEY_LENGTH 31
#define TEXT_LENGTH 7
#define START      32
#define END         122

void print_matrix (unsigned char m[TEXT_LENGTH][KEY_LENGTH]){
    int i,j;

    for (i = 0 ; i < TEXT_LENGTH; i++){
        for (j = 0; j < KEY_LENGTH; j++){
            printf("%02x ", m[i][j]);
        }
        printf("\n");
    }
    
    return;
}

int one_pad_crack_row (unsigned char m[TEXT_LENGTH][TEXT_LENGTH], unsigned char values[TEXT_LENGTH]){
    int end = 0;
    int i,j,k,l;
    char c[100];
    int debug;

    int _i, _j, _k;
    i = 0;
    j = 0;
    k = 0;
    values[i] = START;
    j = 1;
    k = 2;
    while (1){

        debug = 0;
        if (debug == 1){
            printf("Checking i:%d j:%d k:%d\n",i,j,k);
            for (_i = 0; _i < TEXT_LENGTH; _i++){
                for (_j = 0; _j < TEXT_LENGTH; _j++){
                    printf("%02x ", m[_i][_j]);
                }
                printf("\n");
            }
        }

        if (values[0] >= END){
            return 0;
        }

        if (i >= (TEXT_LENGTH-1)){
            // Ganaste
            for ( l = 0; l < TEXT_LENGTH; l++)
                printf("%c ", values[l]);

            printf("\n");

            i = 0;
            values[i] += 1;
            j = 1;
            k = 2;
            continue;
            continue;
        }
        if (k >= TEXT_LENGTH) {
            // Cambio de i
            i += 1;
            j = i+1;
            k = j+1;
            continue;
        }
        values[j] = m[i][j] ^ values[i];   
        values[k] = m[i][k] ^ values[i];

        if ((values[j] < 32 || values[j] > 122) ||
                ( values[j] >= 47 && values[j] <= 62) || 
                ( values[j] >= 33 && values[j] <= 43) ||
                ( values[j] == 64) ||
                (values[k] < 32 || values[k] > 122) ||
                ( values[k] >= 47 && values[k] <= 62) || 
                ( values[k] >= 33 && values[k] <= 43) ||
                ( values[k] == 64) ||
                ( (values[j] ^ values[k]) != m[j][k])){

            i = 0;
            values[i] += 1;
            j = 1;
            k = 2;
        }
        else{
            j++;
            k++;
        }
    }

    return 0;
}

int one_pad_check ( unsigned char m[TEXT_LENGTH][KEY_LENGTH], unsigned char xor[TEXT_LENGTH][KEY_LENGTH][KEY_LENGTH]) {

    int i,j,k;

  for (i = 0; i < TEXT_LENGTH; i++){
      for (j = 0; j < KEY_LENGTH; j++){
          for (k = 0; k < KEY_LENGTH; k++){
              if (xor[i][j][k] != m[i][j] ^ m[k][j]){
                  return 0;                
              }
          }
      }
  }
  return 1;

}

main(){
  unsigned char ch;
  FILE *fpIn, *fpOut;
  int i,j,k,l,line;
  unsigned char key[KEY_LENGTH] = {0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00, 0x00,
				   0x00, 0x00, 0x00};
  // Of course, I did not use the all-0 key when generating the TEXT_LENGTH ciphertexts above!
  unsigned char ttext[TEXT_LENGTH][KEY_LENGTH];
  unsigned char xor[KEY_LENGTH][TEXT_LENGTH][TEXT_LENGTH];
  unsigned char try[TEXT_LENGTH][KEY_LENGTH];
  unsigned char values[KEY_LENGTH][TEXT_LENGTH];

  fpIn = fopen("input.txt", "r");
  fpOut = fopen("output.txt", "w");

  i=0;
  line=0;

  while (fscanf(fpIn, "%02X", &ch) != EOF) {
      if (i == KEY_LENGTH) {
          line++;
          i = 0;
      }
      ttext[line][i] = ch;
      i++;
  }

  for (i = 0; i < KEY_LENGTH; i++){
      for (j = 0; j < TEXT_LENGTH; j++){
          for (k = 0; k < TEXT_LENGTH; k++){
              xor[i][j][k] = ttext[j][i] ^ ttext[k][i];
          }
      }
  }

  for (i = 0; i < KEY_LENGTH; i++){
      printf("COL %i\n", i);
      one_pad_crack_row(xor[i],values[i]);
  }

  fclose(fpIn);
  fclose(fpOut);

  return;
}
