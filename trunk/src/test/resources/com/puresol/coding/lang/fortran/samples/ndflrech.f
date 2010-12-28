        SUBROUTINE NDFLRECH
      DO 1 K=1,NG
C
CHDJ2000+1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     SURFACE WEIGHTED AND CORNER FLUXES IN THE CENTRAL NODE
C 
      DO 1 I=1,6
      PSI0(I,K)=2*(RJIN0(I,K)+RJOUT0(I,K))
      IF(I_HEX2.GT.0) THEN
       PCI0(I,K) = 2*(RJIC0(I,K) + RJOC0(I,K))
      ENDIF
    1 CONTINUE
      IF(I_HEX2.GT.0) GO TO 200
C
C     SURFACE WEIGHTED FLUXES IN NEIGHBOURING NODES
C
      DO 2 K=1,NG
      DO 2 J=1,6
      J4=J-4  
      DO 3 I=1,6
      PSIN(J,I,K)=2*(RJIN(J,I,K)+RJOUT(J,I,K)) 	
    3 CONTINUE
      IF(J4)4,4,5
C
C     NEIGHBOURING NODES No.1,2,3,4 TO THE CENTRAL NODE
C
    4 CONTINUE
      PSI(J,K) = PSIN(J,J+2,K)
C
C     ISYM_R = 31
C
      IF(ISYM_R.EQ.31)  PSI(J,K) = PSIN(1,3,K)        
C
C     ISYM_R = 32
C
      IF(ISYM_R.EQ.32)  PSI(3,K) = PSI0(4,K) 
C
C     ISYM_R = 33
C
      IF(ISYM_R.EQ.33)  PSI(2,K) = PSIN(1,5,K)
C
C     ISYM_R = 34
C
      IF(ISYM_R.EQ.34)  PSI(3,K) = PSI0(3,K)          
C
C     ISYM_R = 61
C
      IF(ISYM_R.EQ.61)  PSI(J,K) = PSIN(1,3,K)
C
C     ISYM_R = 62
C        
      IF(ISYM_R.EQ.62) THEN
         PSI(3,K) = PSI0(4,K)
         PSI(4,K) = PSI0(4,K)
      ENDIF
      GO TO 2
C
C     NEIGHBOURING NODES No. 5,6 TO THE CENTRAL NODE
C
    5 CONTINUE
      PSI(J,K) = PSIN(J,J-4,K)
C
C     ISYM_R = 30
C
      IF(ISYM_R.EQ.30.OR.ISYM_R.EQ.182) THEN
         PSI(5,K) = PSIN(2,3,K)
         PSI(6,K) = PSIN(1,3,K)
      ENDIF
C
C     ISYM_R = 31
C
      IF(ISYM_R.EQ.31)  PSI(J,K) = PSIN(1,3,K)
C
C     ISYM_R = 32
C
      IF(ISYM_R.EQ.32) THEN
         PSI(5,K) = PSIN(2,3,K)
         PSI(6,K) = PSIN(1,3,K)
      ENDIF
C
C     ISYM_R = 33
C     PSIN(6,4,K) and PSIN(5,3,K)
C
      IF(ISYM_R.EQ.33) THEN
         PSI(3,K) = PSIN(6,4,K)
         PSI(4,K) = PSIN(5,3,K)
      ENDIF        
C
C     ISYM_R = 60
C
      IF(ISYM_R.EQ.60) THEN
C     PSIN(J=5,I=2,K)
C        PSI(5,K) = PSIN(3,2,K)
         PSI(5,K) = PSIN(5,2,K)
         PSI(6,K) = PSIN(1,5,K)
      ENDIF
C
C     ISYM_R = 61
C
      IF(ISYM_R.EQ.61)  PSI(J,K) = PSIN(1,3,K) 
C
C     ISYM_R = 62
C
      IF(ISYM_R.EQ.62) THEN
         PSI(5,K) = PSI0(2,K)
         PSI(6,K) = PSIN(1,5,K)
      ENDIF
C
C     ISYM_R = 63
C     PSIN(J=3,I=4,K) and PSIN(J=5,I=3,K)
C
      IF(ISYM_R.EQ.63) THEN
C        PSI(3,K) = PSIN(6,4,K)
         PSI(3,K) = PSIN(3,4,K)
C        PSI(4,K) = PSIN(5,5,K)
         PSI(4,K) = PSIN(5,3,K)
      ENDIF
C 
    2 CONTINUE
C
  200 CONTINUE
C
C     TEST PRINT OF SURFACE WEIGHTED AND CORNER FLUXES IN THE CENTRAL 
C     NODE AND SURFACE WEIGHTED FLUXES IN NEIGHBOURING NODES
C
      IF(IPRN2)300,300,301
  301  CONTINUE
       WRITE(LUNREC,1001)
       WRITE(LUNREC,1002)
       WRITE(LUNREC,1003)
       WRITE(LUNREC,1010)
       WRITE(LUNREC,1000)((PSI0(I,K),I=1,6),K=1,2)
C
      IF(I_HEX2.GT.0) THEN
       WRITE(LUNREC,1011)
       WRITE(LUNREC,1000)((PCI0(I,K),I=1,6),K=1,2)
      ELSE   
       WRITE(LUNREC,1020)
       WRITE(LUNREC,1000)(((PSIN(J,I,K),I=1,6),J=1,6),K=1,2)
       WRITE(LUNREC,1021)ISYM_R
       WRITE(LUNREC,1030)
       WRITE(LUNREC,1000)((PSI(J,K),J=1,6),K=1,2)
      ENDIF
C
  300 CONTINUE 
      DO 8 K=1,NG
      DO 7 I=1,5
    7 CONTINUE
    8 CONTINUE
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     TEST PRINT OF NEUTRON FLUXES IN HEXAGON CORNERS
C
      IF(IPRN2)302,302,303
  303 CONTINUE      
      WRITE(LUNREC,1040)
      WRITE(LUNREC,1000)((F(I,K),I=1,6),K=1,2)
  302 CONTINUE
C    
C     CALCULATION OF RADIAL AND AXIAL BUCKLINGS 
C
C
C     TEST PRINT
C
      IF(IPRN2)304,304,305
  305 CONTINUE       
      WRITE(LUNREC,1051)
  304 CONTINUE
C
      DO 9 K=1,NG
      DAJ=AJOUT0(1,K)+AJOUT0(2,K)-AJIN0(1,K)-AJIN0(2,K)
      BZ2(K)=DAJ/(ANZ*D0(K)*AF0(K))
C
C     TEST PRINT
C
      IF(IPRN2)306,306,307
  307 CONTINUE
      WRITE(LUNREC,1000)BZ2(K),DAJ,ANZ,D0(K),AF0(K)
  306 CONTINUE
C
    9 CONTINUE
C+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
C
C     THIS PART OF THE SUBROUTINE DESCRIBING ACCURATELY THE CALCULA-
C     TION OF FNFM AND FNTM IS NOT USED IN THIS MOMENT !!!
C
C 
C+    BETAFT=0.
C+    BETATT=0.
C+    DO 10 J=1,NODN
C+    BETAFT=BETAFT+BETAF(J) 
C+    BETATT=BETATT+BETAT(J)
C+ 10 CONTINUE
C+    SPF=0.
C+    SPT=0.
C+    DO 11 J=1,NODN
C+    SL=BETAF(J)*FNF*AF0(1)+BETAT(J)*FNT*AF0(2)
C     
C     THE STATIONARY CASE ONLY
C 
C+    IF(BETAF(J).LE.0..AND.BETAT(J).LE.0.)SL=1.
C
C+    SPF=SPF+(RLAM(J)*BETAF(J)*FNF*C(J))/SL         
C+    SPT=SPT+(RLAM(J)*BETAT(J)*FNT*C(J))/SL
C+ 11 CONTINUE
C
C     TEST PRINT
C
C+    IF(IPRN2)308,308,309
C+309 CONTINUE
C+    WRITE(LUNREC,1052)
C+    WRITE(LUNREC,1000)SPF,SPT
C+308 CONTINUE
C
C+    FNFM=(1.-BETAFT)*FNF/RKEFF+SPF
C+    FNTM=(1.-BETATT)*FNT/RKEFF+SPT
C
C    
C     FOR THE NEW CALCULATION OF FNFM AND FNTM ARE USED FNF, FNT AND 
C     RKEFF DEFINED IN SUBROUTINE 
C                                NDRECON (STATIONARY CASE)
C                                NDRECNT (TRANSIENT  CASE)  
C     
      FNFM=FNF/RKEFF
      FNTM=FNT/RKEFF
C
C+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
C       
      SA1=SIR+(O/VEF)+DF*BZ2(1)
      SA2=SIA+(O/VET)+DT*BZ2(2)
      BNA2=(SA1-FNFM)/DF+SA2/DT
      BA2=(FNTM*TF+SA2*(FNFM-SA1))/(DF*DT*BNA2)
      DB=1+4*BA2/BNA2
      BF2=-BNA2*(1.-SQRT(DB))/2.
      BT2=-BNA2*(1.+SQRT(DB))/2.
      EF(1)=(DT*BF2+SA2)/TF
      EF(2)=1.
      ET(1)=(DT*BT2+SA2)/TF
      ET(2)=1.
      IF(BF2)104,105,105
  104 B1=SQRT(-BF2)
      GO TO 106 
  105 B1=SQRT(BF2)
  106 CONTINUE
      B2=SQRT(-BT2)
C
C     TEST PRINT
C
      IF(IPRN2)310,310,311
  311 CONTINUE
      WRITE(LUNREC,1054)
      WRITE(LUNREC,1000)FNFM,FNTM,SA1,SIR,SA2,SIA
      WRITE(LUNREC,1055)
      WRITE(LUNREC,1000)BNA2,BA2
      WRITE(LUNREC,1050)
      WRITE(LUNREC,1000)BF2,BT2,EF(1),EF(2),ET(1),ET(2)
      WRITE(LUNREC,1053)
      WRITE(LUNREC,1000)B1,B2
  310 CONTINUE
C
C    
C     CALCULATION OF COEFFICIENTS ALFA,BETA,GAMA,DELTA 
C
      I1=3
      J1=6
      K1=4
      IJ1=I1*J1     
      S=SW1/2.
CGU2001+
      RZ3 = 3.
      SQ3 = SQRT(RZ3)
      S1=S/SQ3
C      S1=S/SQRT(3.)
CHDJ2000+
C     RDIS = S1*2.
C     RDIS = SW1/SQRT(3.)
      RDIS = SW1/SQ3
CHDJ2000-
      RKSI(4)  = SQ3
      RKSI(5)  = SQ3 
      RKSI(11) = SQ3
      RKSI(12) = SQ3
      RKSI(16) =-SQ3
      RKSI(18) = SQ3
C      RKSI(4)=SQRT(3.)
C      RKSI(5)=SQRT(3.) 
C      RKSI(11)=SQRT(3.)
C      RKSI(12)=SQRT(3.)
C      RKSI(16)=-SQRT(3.)
C      RKSI(18)=SQRT(3.)
CGU2001-
C
C     TEST PRINT 
C
      IF(IPRN2)312,312,313
  313 CONTINUE    
      WRITE(LUNREC,1060)
  312 CONTINUE
C
      DO 12 I=1,I1
      DO 12 J=1,J1
      IJ=J+(I-1)*J1
      IF(BF2)107,108,108
  107 ALFA1(I,J)=COSH(S1*B1*RKSI(IJ))
      BETA1(I,J)=SINH(S1*B1*RKSI(IJ))
      GO TO 109
CHDJ2000+1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE FOR CASE WHEN VALUE OF FUNDAMENTAL BUCKLING BF2 = 0
C 
C 108 ALFA1(I,J)=COS(S1*B1*RKSI(IJ))
C     BETA1(I,J)=SIN(S1*B1*RKSI(IJ))
  108 CONTINUE
      ALFA1(I,J) = COS(S1*B1*RKSI(IJ))
      IF(BF2.GT.0.) THEN  
       BETA1(I,J) = SIN(S1*B1*RKSI(IJ))/SIN(B1*RDIS)
      ELSE
       BETA1(I,J) = S1*RKSI(IJ)/RDIS
      ENDIF
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7==
  109 CONTINUE   
      IC1=JALFA(J)-(I-1)*60
      C1=FLOAT(IC1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE TO ANGLE IN RADIANTS
C
C     SSC=S1*B1*SIND(C1)
      SSC=S1*B1*SIN(DEGRAD*C1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
      IF(SSC)160,161,160
  161 C2=1.
      GO TO 162
  160 CONTINUE
      IF(BF2)110,111,111
  110 C2=SINH(SSC)/SSC
      GO TO 112
  111 C2=SIN(SSC)/SSC
  112 CONTINUE
  162 CONTINUE 
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE TO ANGLE IN RADIANTS
C
C     SCC=B1*S*COSD(C1)
      SCC=B1*S*COS(DEGRAD*C1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7== 
      IF(BF2)113,114,114
  113 GAMA1(I,J)=C2*COSH(SCC)
      DELTA1(I,J)=C2*SINH(SCC)
      GO TO 115
CHDJ2000+1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE FOR CASE WHEN  VALUE OF FUNDAMENTAL BUCKLING BF2 = 0
C 
C 114 GAMA1(I,J)=C2*COS(SCC)
C     DELTA1(I,J)=C2*SIN(SCC)
  114 CONTINUE
      IF(BF2.GT.0.) THEN
       GAMA1(I,J)=C2*COS(SCC)
CHDJ2000+
C      DELTA1(I,J)=C2*SIN(SCC)
       DELTA1(I,J)=C2*SIN(SCC)/SIN(B1*RDIS)
CHDJ2000-
      ELSE
       GAMA1(I,J)=1.
       DELTA1(I,J)=S*COS(DEGRAD*C1)/RDIS  
      ENDIF     
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7==
  115 CONTINUE 
      A1(IJ)=ALFA1(I,J) 
      A1(IJ+IJ1)=GAMA1(I,J)
      A1(IJ+2*IJ1)=BETA1(I,J)
      A1(IJ+3*IJ1)=DELTA1(I,J)
C
C     TEST PRINT
C
      IF(IPRN2)314,314,315
  315 CONTINUE
      WRITE(LUNREC,1200)RKSI(IJ),IC1,C2,SCC,ALFA1(I,J),GAMA1(I,J),
     $                  BETA1(I,J),DELTA1(I,J)
  314 CONTINUE
C
   12 CONTINUE
C
C     TEST PRINT
C
      IF(IPRN2)316,316,317
  317 CONTINUE
      WRITE(LUNREC,1061)
      WRITE(LUNREC,1000)(A1(I),I=1,72)
      WRITE(LUNREC,1070)
  316 CONTINUE
C
      DO 13 I=1,I1
      DO 13 J=1,J1
      IJ=J+(I-1)*J1
      ALFA2(I,J)=COSH(S1*B2*RKSI(IJ))
      BETA2(I,J)=SINH(S1*B2*RKSI(IJ))    
      IC1=JALFA(J)-(I-1)*60
      C1=FLOAT(IC1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE TO ANGLE IN RADIANTS
C
C     SSC=S1*B2*SIND(C1)
      SSC=S1*B2*SIN(DEGRAD*C1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7== 
      IF(SSC)163,164,163
  164 C2=1.
      GO TO 165
  163 C2=SINH(SSC)/SSC
  165 CONTINUE
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     CHANGE TO ANGLE IN RADIANTS
C
C     SCC=B2*S*COSD(C1)
      SCC=B2*S*COS(DEGRAD*C1)
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7== 
      GAMA2(I,J)=C2*COSH(SCC)
      DELTA2(I,J)=C2*SINH(SCC)
      A1(IJ+4*IJ1)=ALFA2(I,J) 
      A1(IJ+5*IJ1)=GAMA2(I,J)
      A1(IJ+6*IJ1)=BETA2(I,J)
      A1(IJ+7*IJ1)=DELTA2(I,J)
C
C     TEST PRINT
C
      IF(IPRN2)318,318,319
  319 CONTINUE
      WRITE(LUNREC,1200)RKSI(IJ),IC1,C2,SCC,ALFA2(I,J),GAMA2(I,J),
     $                  BETA2(I,J),DELTA2(I,J)
  318 CONTINUE
C
   13 CONTINUE
C
C     TEST PRINT
C
      IF(IPRN2)320,320,321
  321 CONTINUE
      WRITE(LUNREC,1071)
      WRITE(LUNREC,1000)(A1(I),I=73,144)
  320 CONTINUE
C
C                                    
C     CALCULATION OF FUNDAMENTAL AND TRANSIENT CORNER AND SURFACE WEIG
C     HTED FLUXES IN HEXAGON 
C             
      DEF=EF(1)-ET(1)
C
C     TEST PRINT
C
      IF(IPRN2)322,322,323
  323 CONTINUE
      WRITE(LUNREC,1080)
      WRITE(LUNREC,1081)
  322 CONTINUE
C
      DO 14 I=1,I1
      FF(I)=(F(I,1)-ET(1)*F(I,2))/DEF
      FF(I+I1)=(F(I+I1,1)-ET(1)*F(I+I1,2))/DEF
      FT(I)=(EF(1)*F(I,2)-F(I,1))/DEF
      FT(I+I1)=(EF(1)*F(I+I1,2)-F(I+I1,1))/DEF
      FFS=(FF(I)+FF(I+I1))/2.
      FFA=(FF(I)-FF(I+I1))/2.
      FTS=(FT(I)+FT(I+I1))/2.
      FTA=(FT(I)-FT(I+I1))/2. 
      PSI0F(I)=(PSI0(I,1)-ET(1)*PSI0(I,2))/DEF
      PSI0F(I+I1)=(PSI0(I+I1,1)-ET(1)*PSI0(I+I1,2))/DEF
      PSI0T(I)=(EF(1)*PSI0(I,2)-PSI0(I,1))/DEF
      PSI0T(I+I1)=(EF(1)*PSI0(I+I1,2)-PSI0(I+I1,1))/DEF
      PSI0FS=(PSI0F(I)+PSI0F(I+I1))/2.
      PSI0FA=(PSI0F(I)-PSI0F(I+I1))/2.     
      PSI0TS=(PSI0T(I)+PSI0T(I+I1))/2.
      PSI0TA=(PSI0T(I)-PSI0T(I+I1))/2.
      R1(I)=FFS
      R1(I+I1)=PSI0FS
      R1(I+2*I1)=FFA                             
      R1(I+3*I1)=PSI0FA 
      R1(I+4*I1)=FTS                             
      R1(I+5*I1)=PSI0TS 
      R1(I+6*I1)=FTA                             
      R1(I+7*I1)=PSI0TA
C
C     TEST PRINT
C
      IF(IPRN2)324,324,325
  325 CONTINUE
      WRITE(LUNREC,1000)FFS,PSI0FS,FFA,PSI0FA,FTS,PSI0TS,FTA,PSI0TA
  324 CONTINUE
C   
   14 CONTINUE
C
C     TEST PRINT
C
      IF(IPRN2)326,326,327
  327 CONTINUE
      WRITE(LUNREC,1082)
      WRITE(LUNREC,1000)(R1(I),I=1,24)
  326 CONTINUE
C 
C
C     CALCULATION OF FLUX RECONSTRUCTION COEFFICIENTS  
C
      DO 15 K=1,K1
      KS=2*(K-1)
      KL=2*K-1 
      DO 16 I=1,I1
      R(I)=R1(I+KS*I1)
      R(I+I1)=R1(I+KL*I1)
   16 CONTINUE
      DO 17 J=1,J1 
      DO 18 I=1,I1        
      A(I+(J-1)*J1)=A1(J+(I-1)*J1+KS*IJ1)
      A(I+I1+(J-1)*J1)=A1(J+(I-1)*J1+KL*IJ1)
   18 CONTINUE                
   17 CONTINUE
C
C     TEST PRINT
C
      IF(IPRN2)328,328,329
  329 CONTINUE
      WRITE(LUNREC,1090)
      WRITE(LUNREC,1000)(R(J),J=1,6)
      WRITE(LUNREC,1091)
      WRITE(LUNREC,1000)(A(J),J=1,36)
  328 CONTINUE
C
      CALL NDGELG(R,A,6,1,EPS,IER)
C
C     TEST PRINT
C
      IF(IPRN2)330,330,331
  331 CONTINUE
      WRITE(LUNREC,1092)
      WRITE(LUNREC,1300)IER,(R(J),J=1,6)
  330 CONTINUE
C
      DO 19 J=1,J1
      CK(J+(K-1)*J1)=R(J)
   19 CONTINUE  
   15 CONTINUE 
C
C     TEST PRINT
C
      IF(IPRN2)332,332,333
  333 CONTINUE
      WRITE(LUNREC,1093)
      WRITE(LUNREC,1000)(CK(K),K=1,24) 
  332 CONTINUE
C   
C     RECONSTRUCION OF FAST AND THERMAL NEUTRON FLUX OR THERMAL POWER
C     DISTRIBUTION INSIDE THE HEXAGON
C 
      IF(ITEST-1) 20,21,21
   20 CONTINUE
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1006)
      WRITE(LUNREC,1007)
      IF(IREC)58,58,59
   58 CONTINUE
      WRITE(LUNREC,1014)
      WRITE(LUNREC,1015)
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1110)
      GO TO 60
   59 CONTINUE
      WRITE(LUNREC,1016)
      WRITE(LUNREC,1017)
   60 CONTINUE
      CALL NDFLUX_H(XT,YT,F1)
C
C     PRINT OF RESULT IN SELECTED POINT
C 
      IF(IREC)61,61,62
   61 CONTINUE 
      WRITE(LUNREC,1000)XT,YT,F1(1),F1(2)
      RETURN
   62 CONTINUE
C     PPR=(SFF*F1(1)+SFT*F1(2))/1.0E+13
      PPR=(SFF*F1(1)+SFT*F1(2))
      GO TO 21
  350 CONTINUE
      PP=RALFA1*PPR
      WRITE(LUNREC,1018)RALFA1
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1112)
      WRITE(LUNREC,1000)XT,YT,PPR,PP
      RETURN
C
   21 CONTINUE 
C
C     MAXIMUM SIZE OF THE GRID     : NX=200, NY=200
C     RECOMMENDED SIZE OF THE GRID : NX=100, NY=100
C 
CGU2001+
      RZ3 = 3.
      SQ3 = SQRT(RZ3)
      S3=1./SQ3
C      S3=1/SQRT(3.)
      AW=2.*S3*SW1
      A2=AW/2.
      PH=SW1*SW1*SQ3/2.
C      PH=SW1*SW1*SQRT(3.)/2
CGU2001-
      DX=GS
      DY=GS
      PE=DX*DY
      NX=INT(SW1/DX)+2
      NY=INT(AW/DY)+2
      IF(NX.GT.200.OR.NY.GT.200) GO TO 25
      L=1
C
      IF(IREC)63,63,64
   63 CONTINUE
      DO 50 I=1,NX
      DO 50 J=1,NY
      P1(I,J)=0.0
      P2(I,J)=0.0
   50 CONTINUE
      GO TO 65
   64 CONTINUE
      DO 66 I=1,NX
      DO 66 J=1,NY
      P(I,J)=0.0
   66 CONTINUE
   65 CONTINUE 
C      
      TP1=0.0
      TP2=0.0
      TP=0.0 
      DO 22 I=1,NX
      X(I)=(I-1)*DX
      IF(X(I).GE.SW1) X(I)=SW1
      X1(I)=X(I)-S 
      DO 23 J=1,NY
      Y(J)=(J-1)*DY
      IF(Y(J).GE.AW) Y(J)=AW
      Y1(J)=Y(J)-A2
      HH = 1.
      YUB=S3*(SW1-SIGN(HH,X1(I))*X1(I))
      YLB=-YUB
      IF(Y1(J).LT.YLB.OR.Y1(J).GT.YUB) GO TO 24
      CALL NDFLUX_H(X1(I),Y1(J),F1)
      IF(IREC)67,67,68
   67 CONTINUE   
      P1(I,J)=F1(1) 
      P2(I,J)=F1(2)
      TP1=TP1+F1(1)
      TP2=TP2+F1(2)
      GO TO 69
   68 CONTINUE
C     PP=(SFF*F1(1)+SFT*F1(2))/1.0E+13
      PP=(SFF*F1(1)+SFT*F1(2))
      TP=TP+PP
      P(I,J)=PP 
   69 CONTINUE
      IF(L-1) 51,51,52
   51 CONTINUE
      IF(IREC)70,70,71
   70 CONTINUE 
      P1MIN=F1(1)
      X1MIN=X1(I)
      Y1MIN=Y1(J)
      P2MIN=F1(2)
      X2MIN=X1(I)
      Y2MIN=Y1(J)
      P1MAX=F1(1)
      X1MAX=X1(I)
      Y1MAX=Y1(J)
      P2MAX=F1(2)
      X2MAX=X1(I)
      Y2MAX=Y1(J)
      GO TO 73
   71 CONTINUE
      PMIN=PP
      XMIN=X1(I)
      YMIN=Y1(J)
      PMAX=PP
      XMAX=X1(I)
      YMAX=Y1(I)
   73 CONTINUE
      GO TO 53
   52 CONTINUE
      IF(IREC) 74,74,75
   74 CONTINUE
      IF(F1(1).GE.P1MIN) GO TO 54
      P1MIN=F1(1)
      X1MIN=X1(I)
      Y1MIN=Y1(J)
   54 CONTINUE         
      IF(F1(2).GE.P2MIN) GO TO 55
      P2MIN=F1(2)   
      X2MIN=X1(I)
      Y2MIN=Y1(J)
   55 CONTINUE
      IF(F1(1).LE.P1MAX) GO TO 56
      P1MAX=F1(1)
      X1MAX=X1(I)
      Y1MAX=Y1(J)
   56 CONTINUE
      IF(F1(2).LE.P2MAX) GO TO 57
      P2MAX=F1(2)
      X2MAX=X1(I)
      Y2MAX=Y1(J)
   57 CONTINUE
      GO TO 76
   75 CONTINUE
      IF(PP.GE.PMIN) GO TO 77
      PMIN=PP
      XMIN=X1(I)
      YMIN=Y1(J)
   77 CONTINUE
      IF(PP.LE.PMAX) GO TO 78
      PMAX=PP
      XMAX=X1(I)
      YMAX=Y1(J)
   78 CONTINUE
   76 CONTINUE
   53 CONTINUE
      L=L+1
C
C     TEST PRINT
C 
      IF(ITEST-1)336,337,336
  337 CONTINUE
      IF(IPRN2)334,334,335
  335 CONTINUE
      IF(IREC)79,79,80
   79 CONTINUE
      WRITE(LUNREC,1094)
      WRITE(LUNREC,1500)P1MIN,X1MIN,Y1MIN,P1MAX,X1MAX,Y1MAX,F1(1),
     $P2MIN,X2MIN,Y2MIN,P2MAX,X2MAX,Y2MAX,F1(2)
      GO TO 81
   80 CONTINUE
      WRITE(LUNREC,1095)
      WRITE(LUNREC,1600)PMIN,XMIN,YMIN,PMAX,XMAX,YMAX   
   81 CONTINUE 
  334 CONTINUE 
  336 CONTINUE   
C
   24 CONTINUE
      IF(ITEST)338,338,339
  339 CONTINUE
C
C     TEST PRINT
C
C     WRITE(LUNREC,1400)I,J,X(I),Y(J),X1(I),Y1(J),YUB,YLB,P1(I,J),
C    $P2(I,J)          
C
  338 CONTINUE
   23 CONTINUE
   22 CONTINUE
C
C     PRINT OF MINIMUM AND MAXIMUM NEUTRON FLUX DENSITIES OR THERMAL
C     POWER DENSITIES AND THEIR COORDINATES IN THE HEXAGONAL CASSETTE
C
      IF(ITEST-1)340,341,343
  341 CONTINUE
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1004)
      WRITE(LUNREC,1005)
      WRITE(LUNREC,1006)
      WRITE(LUNREC,1007)
  343 CONTINUE 
      IF(IREC)82,82,83
   82 CONTINUE
      IF(ITEST.EQ.1) THEN 
       WRITE(LUNREC,1014)
       WRITE(LUNREC,1015)
      ENDIF
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1120)P1MIN,X1MIN,Y1MIN
      WRITE(LUNREC,1121)P1MAX,X1MAX,Y1MAX                                       
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1130)P2MIN,X2MIN,Y2MIN
      WRITE(LUNREC,1131)P2MAX,X2MAX,Y2MAX
      WRITE(LUNREC,1001)
      TPA1=PE*TP1/PH
      TPA2=PE*TP2/PH
      WRITE(LUNREC,1140)TPA1,AF0(1)
      WRITE(LUNREC,1141)TPA2,AF0(2)
      WRITE(LUNREC,1001)
      GO TO 84
   83 CONTINUE
  340 CONTINUE
C
C     RALFA=P0*PH/(PE*TP)
C     RALFA IS DEPENDENT ON THE GRID STEP
C
      P01=AF0(1)*SFF+AF0(2)*SFT
C
C     RALFA IS INDEPENDENT ON THE GRID STEP
C
      RALFA1=RALFA*1.0E-09
      P0=P01*RALFA1
      IF(ITEST.LE.0) GO TO 350
C     TPA=PE*TP/PH
      TPA=P0
      PMIN1=RALFA1*PMIN
      PMAX1=RALFA1*PMAX
      IF(ITEST.EQ.1) THEN      
       WRITE(LUNREC,1017)
      ENDIF
      WRITE(LUNREC,1001)
      WRITE(LUNREC,1018)RALFA1
      IF(TPA .LT. 1.0D-69) THEN
       PMIN1 = 0.0
       XMIN  = 0.0
       YMIN  = 0.0
       PMAX1 = 0.0
       XMAX  = 0.0
       YMAX  = 0.0
C
C      DIVIDING BY TPA IS NOT POSSIBLE THEREFORE
C
       RMIN  = 0.0
       RMAX  = 0.0
C 
       WRITE(LUNREC,1132)PMIN1,XMIN,YMIN
       WRITE(LUNREC,1133)PMAX1,XMAX,YMAX
       WRITE(LUNREC,1142)TPA 
       WRITE(LUNREC,1001)
       RETURN 
      ENDIF 
      WRITE(LUNREC,1132)PMIN1,XMIN,YMIN
      WRITE(LUNREC,1133)PMAX1,XMAX,YMAX
      WRITE(LUNREC,1142)TPA     
      RMIN=PMIN1/TPA
      RMAX=PMAX1/TPA
      WRITE(LUNREC,1143)RMIN
      WRITE(LUNREC,1144)RMAX
      WRITE(LUNREC,1001)
C
   84 CONTINUE
C
      IF(ITEST.EQ.2) RETURN      
C
      IF(IREC)85,85,86
   85 CONTINUE
      WRITE(LUNREC,1111)
      GO TO 87
   86 CONTINUE
      WRITE(LUNREC,1113)
   87 CONTINUE
      HH = 1.  
      DO 27 I=1,NX
      DO 28 J=1,NY
      YUB=S3*(SW1-SIGN(HH,X1(I))*X1(I))
      YLB=-YUB
      IF(Y1(J).LT.YLB.OR.Y1(J).GT.YUB) GO TO 29
      GO TO 30           
   29 CONTINUE
      IF(IREC)88,88,89
   88 CONTINUE   
      P1(I,J)=P1MIN
      P2(I,J)=P2MIN
      GO TO 90
   89 CONTINUE
      P(I,J)=PMIN
   90 CONTINUE
   30 CONTINUE
C
C     PRINT OF FAST AND THERMAL NEUTRON FLUX DENSITIES OR THERMAL
C     POWER DENSITIES IN GRID POINTS
C 
      IF(IREC)91,91,92
   91 CONTINUE        
      WRITE(LUNREC,1000)X(I),Y(J),X1(I),Y1(J),P1(I,J),P2(I,J)
      GO TO 93
   92 CONTINUE
      P(I,J)=RALFA1*P(I,J)
      WRITE(LUNREC,1000)X(I),Y(J),X1(I),Y1(J),P(I,J)
C
   93 CONTINUE
   28 CONTINUE 
   27 CONTINUE
      RETURN
   25 CONTINUE
      WRITE(LUNREC,1100)
      RETURN
      END
