      SUBROUTINE NDOALB_C(ITRAN,INNUM1,INNUM2,INTYP1,INTYP2,
     $                    CPCI,CPCO,DF,DT,SIR,SIA,
     $                    FNF,FNT,TF)                                       
                           IF (FNF(INOD1) .EQ. 0.0 
     $                            .AND. FNT(INOD1) .EQ. 0.0
     $                            .AND. FNF(INOD2) .EQ. 0.0
     $                            .AND. FNT(INOD2) .EQ. 0.0) THEN 
                              ITYPE = 1
                           ENDIF
      END                                                    
C
