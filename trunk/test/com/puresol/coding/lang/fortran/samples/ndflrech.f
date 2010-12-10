        SUBROUTINE NDFLRECH
C----------------------------------------------------------------------
C     PURPOSE:
C
C     RECONSTRUCTION OF NEUTRON FLUX DISTRIBUTION INSIDE THE HEXAGONAL
C     NODE
C----------------------------------------------------------------------
C     METHOD:
C     METHOD OF SUCCESSIVE SMOOTHING WITH ANALYTICAL SOLUTION (MSS-AS)
C----------------------------------------------------------------------
C     REFERENCES:
C     H.FINNEMANN,R.BOER,R.BOHM,R.MULLER:EVALUATION OF SAFETY PARAMETER
C     S IN NODAL SPACE-TIME NUCLEAR REACTOR ANALYSIS,SPECIALISTS MEETIN
C     G ON ADVANCED CALCULATIONAL METHODS FOR POWER REACTORS,CARADACHE,
C     FRANCE,10-14 SEPTEMBER 1990
C
C     R.BOER,H.FINNEMANN:FAST ANALYTICAL FLUX RECONSTRUCTION METHOD FOR
C     NODAL SPACE-TIME NUCLEAR REACTOR ANALYSIS,ANNALS OF NUCLEAR ENERG
C     Y VOL.19,NO.10-12,PP.617-628,1992
C----------------------------------------------------------------------
C     CALLED FROM : NDRECON
C     CALLS       : NDGELG, NDFLUX_H
C----------------------------------------------------------------------
C     AUTHOR:       JAN HADEK (HDJ)                   DATE: 15.10. 1993
C----------------------------------------------------------------------
C     MODIFIED BY : HDJ                               DATE:  3.12. 1993
C                   HDJ                               DATE: 25.11. 1996                       
C     MODIFIED BY : GU                                DATE: 06.04. 1998
C
C     REMARK      : THE FOLLOWING ARRAY IS RENAMED: 
C                   KSI ==> RKSI (FOR IMPLICITE STATEMENT)
C                   SIN and COS FUNCTION ARGUMENTS CHANGED 
C                   FROM DEGREE TO RADIANT
C
C     MODIFIED BY : GU                                DATE: 28.07. 2000
C
C     REMARK      : HELP VARIABLE HH IN SIGN INTRODUCED
C
C     MODIFIED BY : HDJ ( CHDJ2000+,...,CHDJ2000- )   DATE: 03.11. 2000
C                                                          -15.12. 2000  
C     MODIFIED BY : GU                                DATE : 09.02.2001
C                       Square roots SQRT(3.) removed
C     MODIFIED BY : GU       FORTRAN90                DATE: 03.07.2002 
CGU--------------------------------------------------------------------
C
      USE CNDLUN, ONLY: LUNREC  
      USE CNDCOR, ONLY: I_HEX2
C
      USE CNDIRC, ONLY: NREC,KZ,IREC,IPRN2,ITEST,IPRN1,ISYM_R,JPSIN
      USE CNDRE1, ONLY: GS,XT,YT,ANZ,SW1
      USE CNDRE5, ONLY: RMAX,RMIN

      USE CNDREC_H, ONLY: RJIN0,RJOUT0,RJIN,RJOUT,AJIN0,AJOUT0,
     $                    RJIC0,RJOC0,  
     $                    AF0,AF,RALFA,                        
     $                    DF,SIR,FNF,SFF,DT,SIA,FNT,SFT,TF,D,  
     $                    RKEFF,O,VEF,VET,
     $                    B1,B2,BF2,EF,ET,CK,JALFA
C
      IMPLICIT REAL(8) (A-H,O-Z)
C
      DIMENSION RKSI(18)
      DIMENSION PSI0(6,2),PSIN(6,6,2),PSI(6,2),F(6,2),D0(2),BZ2(2),
     $          PSI0F(6),PSI0T(6),R1(24),R(6),  
     $          A1(144),A(36),FF(6),FT(6),F1(2),
     $          X(200),Y(200),X1(200),Y1(200), 
     $          P2(200,200),PCI0(6,2)
      TARGET :: ALFA1(3,6),BETA1(3,6),GAMA1(3,6),DELTA1(3,6)
      TARGET :: P1(200,200)
      POINTER :: ALFA2(:,:),BETA2(:,:),GAMA2(:,:),DELTA2(:,:),
     $           P(:,:)
C
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7== 
C 
C      EQUIVALENCE (ALFA1(1,1),ALFA2(1,1)),(BETA1(1,1),BETA2(1,1)), 
C     $            (GAMA1(1,1),GAMA2(1,1)),(DELTA1(1,1),DELTA2(1,1)),
C     $            (P1(1,1),P(1,1))
C                          
C      DATA RKSI /2.,1.,-1.,1.,1.,0.,1.,2.,1.,0.,1.,1.,-1.,1.,2.,1.,
C     $          0.,1./
C      DATA JALFA/30,90,150,0,60,120/
C      DATA EPS /0.000001/
C     DATA R1,R,A1,A /24*0.0,6*0.0,144*0.0,36*0.0/
C     DATA X,Y,X1,Y1 /200*0.0,200*0.0,200*0.0,200*0.0/
C
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C      DATA DEGRAD /0.01745329251994/
CGU=+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C 
C----------------------------------------------------------------------
C     VARIABLES DESCRIPTION:
C
C     IHEX.....CONTROLER
C              IHEX=0   RECTANGULAR GEOMETRIE OF FUEL CASSETTES
C              IHEX=1   HEXAGONAL GEOMETRIE OF FUEL CASSETTES
C     I_HEX2...CONTROLER
C              I_HEX2=0 HEXNEM1 METHOD IS USED (PARTIAL CURRENTS AT
C                       THE SIDES)
C              I_HEX2>0 HEXNEM2 METHOD IS USED (PARTIAL CURRENTS AT
C                       THE CORNERS AND SIDES)
C     IREC.....IREC=0  RECONSTRUCTION OF NEUTRON FLUX DENSITY
C              IREC=1  RECONSTRUCTION OF THERMAL POWER DENSITY
C     IPRN2....IPRN2=0  WITHOUT TEST PRINT
C              IPRN2=1  TEST PRINT           
C     ISYM_R.....SYMMETRY - FOR THE CASSETTE AT THE SYMMETRY BOUNDARY
C              (ISYM=30,31,182,360)
C     JPSIN....NUMBER OF NEIGHBOURING NODES USED FOR NEUTRON FLUX
C              RECONSTRUCTION
C     ANZ......HEIGHT OF THE NODE IN THE AXIAL DIRECTION
C     SW1......DISTANCE BETWEEN OPPOSITE FACES OF THE HEXAGON
C     RDIS.....DISTANCE BETWEEN THE CENTRE AND THE CORNER 
C              OF THE HEXAGON
C     ITEST....ITEST=0  RECONSTRUCTION IN SELECTED POINT 
C              ITEST=1  CREATION OF THE GRID
C              ITEST=2  CREATION OF THE GRID WITHOUT PRINTING
C     XT,YT....COORDINATES OF SELECTED POINT     
C     GS.......GRID STEP  
C     RJIN0....PARTIAL RADIAL SIDE CURRENTS INCOMING INTO THE CENTRAL
C              NODE 0
C     RJOUT0...PARTIAL RADIAL SIDE CURRENTS OUTGOING FROM THE CENTRAL
C              NODE 0
C     RJIC0....PARTIAL RADIAL CORNER CURRENTS INCOMING INTO THE CENTRAL
C              NODE 0
C     RJOC0....PARTIAL RADIAL CORNER CURRENTS OUTGOING FROM THE CENTRAL
C              NODE 0
C     RJIN.....PARTIAL RADIAL CURRENTS INCOMING INTO THE NEIGHBOURING 
C              NODES
C     RJOUT....PARTIAL RADIAL CURRENTS OUTGOING FROM THE NEIGHBOURING 
C              NODES
C     AJIN0....PARTIAL AXIAL SIDE CURRENTS INCOMING INTO THE CENTRAL NODE 0
C     AJOUT0...PARTIAL AXIAL SIDE CURRENTS OUTGOING FROM THE CENTRAL NODE 0
C     AF0......NODAL FLUX IN THE CENTRAL NODE 0
C     AF.......NODAL FLUXES IN THE NEIGHBOURING NODES
C     RALFA....NORMALIZATION COEFFICIENT OF THERMAL POWER DENSITY
C     P0.......THERMAL POWER DENSITY IN THE CENTRAL NODE 0
C     DF-TF....MACROSCOPIC DIFFUSION CONSTANTS ( SEE DYN3D)
C     D........DIFFUSION COEFFICIENTS OF NEIGHBOURING NODES
C     RKEFF....KEFF VALUE
C     O........LOCAL PERIODE
C     VEF,VET..NEUTRON VELOCITIES (SEE DYN3D)
C     NODN.....NUMBER OF GROUP OF PRECURSORS OF DELAYED NEUTRONS
C     RLAM.....DECAY CONSTANTS OF PRECURSORS (SEE DYN3D)
C     BETAF-T..BETA VALUES OF FAST AND THERMAL GROUP (SEE DYN3D)
C     C........PRECURSORS CONCENTRATION
C     NG.......NUMBER OF ENERGY GROUPS 
C     PSI0.....SURFACE WEIGHTED FLUXES IN THE CENTRAL NODE
C     PCI0.....CORNER FLUXES IN THE CENTRAL NODE 
C     PSIN.....SURFACE WEIGHTED FLUXES IN THE NEIGHBOURING NODES
C     PSI......SURFACE WEIGHTED FLUXES IN THE NEIGHBOURING NODES
C              USED FOR CALCULATION OF CORNER FLUXES
C     F01-F03..PARTIAL CORNER FLUXES 
C     F........NEUTRON FLUXES IN THE HEXAGON CORNERS
C     BF2......FUNDAMENTAL RADIAL BUCKLING
C     BT2......TRANSIENT RADIAL BUCKLING
C     BZ2......AXIAL BUCKLING 
C     EF.......COEFFICIENTS OF FUNDAMENTAL SOLUTION
C     ET.......COEFFICIENTS OF TRANSIENT SOLUTION
C     YUB......Y COORDINATES OF UPPER BOUNDARY OF HEXAGON
C     YLB......Y COORDINATES O LOWER BOUNDARY OF HEXAGON 
C-----------------------------------------------------------------------
C
CHDJ2000+1====+====2====+====3====+====4====+====5====+====6====+====7== 
C
 1000 FORMAT(6(1X,1PE12.5))
 1001 FORMAT(/)
 1002 FORMAT(1X,'TEST PRINT')
 1003 FORMAT(11('-'))
 1004 FORMAT(1X,'X   :',1X,'X-COORDINATES OF THE SURFER GRID [cm]')
 1005 FORMAT(1X,'Y   :',1X,'Y-COORDINATES OF THE SURFER GRID [cm]')
 1006 FORMAT(1X,'X1  :',1X,'X-COORDINATES IN THE HEXAGONAL CASSETTE [cm]
     $')
 1007 FORMAT(1X,'Y1  :',1X,'Y-COORDINATES IN THE HEXAGONAL CASSETTE [cm]
     $')
 1100 FORMAT(1X,'NX AND NY MUST BE LOWER OR EGUAL 200 !')
 1110 FORMAT(6X,'X1',11X,'Y1',11X,'FFST',9X,'FTHR')
 1111 FORMAT(7X,'X',12X,'Y',12X,'X1',11X,'Y1',9X,'FFST',9X,'FTHR')
 1112 FORMAT(6X,'X1',11X,'Y1',11X,'PR',11X,'P')
 1113 FORMAT(7X,'X',12X,'Y',12X,'X1',11X,'Y1',10X,'P') 
 1014 FORMAT(1X,'FFST:',1X,'FAST NEUTRON FLUX DENSITY [1/(cm**2*s)]')
 1015 FORMAT(1X,'FTHR:',1X,'THERMAL NEUTRON FLUX DENSITY [1/(cm**2*s)]')
 1016 FORMAT(1X,'PR  :',1X,'THERMAL POWER DENSITY [rel. units ]')
 1017 FORMAT(1X,'P   :',1X,'THERMAL POWER DENSITY [MW/cm**3]')
 1018 FORMAT(1X,'RALFA - NORMALIZATION COEFFICIENT OF THERMAL POWER',
     $          ' DENSITY:'1X,1PE12.5)
C
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
 1120 FORMAT(1X,'MINIMUM FAST FLUX   :',1X,1PE12.5,1X,'[1/(cm**2*s)]',
     $1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,'Y1:',1X,1PE12.5,1X,
     $'[cm]') 
 1121 FORMAT(1X,'MAXIMUM FAST FLUX   :',1X,1PE12.5,1X,'[1/(cm**2*s)]',
     $1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,'Y1:',1X,1PE12.5,1X,
     $'[cm]')  
 1130 FORMAT(1X,'MINIMUM THERMAL FLUX:',1X,1PE12.5,1X,'[1/(cm**2*s)]',
     $1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,'Y1:',1X,1PE12.5,1X,
     $'[cm]') 
 1131 FORMAT(1X,'MAXIMUM THERMAL FLUX:',1X,1PE12.5,1X,'[1/(cm**2*s)]',
     $1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,'Y1:',1X,1PE12.5,1X,
     $'[cm]')
 1132 FORMAT(1X,'MINIMUM THERMAL POWER DENSITY:',1X,1PE12.5,1X,
     $'[MW/cm**3]',1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,
     $'Y1:',1X,1PE12.5,1X,'[cm]') 
 1133 FORMAT(1X,'MAXIMUM THERMAL POWER DENSITY:',1X,1PE12.5,1X,
     $'[MW/cm**3]',1X,'COORDINATES: X1:',1X,1PE12.5,1X,'[cm]',1X,
     $'Y1:',1X,1PE12.5,1X,'[cm]')
 1140 FORMAT(1X,'AVERAGE FAST FLUX - RECONSTRUCTED   :',1X,1PE12.5,1X,
     $'[1/(cm**2*s)]',1X,'CALCULATED BY DYN3D:',1X,1PE12.5,1X,
     $'[1/(cm**2*s)]')
 1141 FORMAT(1X,'AVERAGE THERMAL FLUX - RECONSTRUCTED:',1X,1PE12.5,1X,
     $'[1/(cm**2*s)]',1X,'CALCULATED BY DYN3D:',1X,1PE12.5,1X,
     $'[1/(cm**2*s)]')
 1142 FORMAT(1X,'AVERAGE THERMAL POWER DENSITY - CALCULATED BY DYN3D:',
     $1X,1PE12.5,1X,'[MW/cm**3]')
 1143 FORMAT(1X,'MINIMUM THERMAL POWER DENSITY/AVERAGE THERMAL POWER DEN
     $SITY - CALCULATED BY DYN3D:',1X,1PE12.5)
 1144 FORMAT(1X,'MAXIMUM THERMAL POWER DENSITY/AVERAGE THERMAL POWER DEN
     $SITY - CALCULATED BY DYN3D:',1X,1PE12.5)
C
C     TEST PRINT FORMATS
C
CHDJ2000+
 1010 FORMAT(1X,'PSI0(I,K):')
 1011 FORMAT(1X,'PCI0(I,K):')
 1020 FORMAT(1X,'PSIN(I,J,K):')
 1021 FORMAT(1X,'ISYM: ',I3) 
 1030 FORMAT(1X,'PSI(J,K):')
 1031 FORMAT(1X,'K: ',I2,'I: ',I2)
 1032 FORMAT(1X,'AF0(K),AF(I,K),AF(I+1,K):')
 1033 FORMAT(1X,'AF0(K),AF(6,K),AF(1,K):')
 1034 FORMAT(1X,'F01,F02,F03:')
 1035 FORMAT(1X,'D0(K),D(I,K),D(I+1,K):')
 1036 FORMAT(1X,'D0(K),D(6,K),D(1,K):')
CHDJ2000-
 1040 FORMAT(1X,'F(I,K):')
 1050 FORMAT(1X,'BF2,BT2,EF(1),EF(2),ET(1),ET(2):') 
 1051 FORMAT(1X,'BZ2(K),DAJ,AN,D0(K),AF0(K):')
 1052 FORMAT(1X,'SPF,SPT:')
 1053 FORMAT(1X,'B1,B2:')
 1054 FORMAT(1X,'FNFM,FNTM,SA1,SIR,SA2,SIA:')
 1055 FORMAT(1X,'BNA2,BA2:') 
 1060 FORMAT(1X,'RKSI,IC1,C2,SCC,ALFA1,GAMA1,BETA1,DELTA1 (I,J):')
 1061 FORMAT(1X,'A1(1)-A1(72):')
 1070 FORMAT(1X,'RKSI,IC1,C2,SCC,ALFA2,GAMA2,BETA2,DELTA2 (I,J):')
 1071 FORMAT(1X,'A1(73)-A1(144):')
 1080 FORMAT(1X,'FFS,PSI0FS,FFA,PSI0FA,FTS,PSI0TS:')
 1081 FORMAT(1X,'FTA,PSI0TA:')
 1082 FORMAT(1X,'R1(1)-R1(24):')
 1090 FORMAT(1X,'R(1)-R(6) - INPUT:')
 1091 FORMAT(1X,'A(1)-A(36) - INPUT:')
 1092 FORMAT(1X,'IER,R(1)-R(6) - OUTPUT:')
 1093 FORMAT(1X,'CK(1)-CK(24):')
 1094 FORMAT(4X,'P1MIN',6X,'X1MIN',2X,'Y1MIN',4X,'P1MAX',6X,'X1MAX',2X,
     $'Y1MAX',3X,'F1(1)',8X,'P2MIN',6X,'X2MIN',2X,'Y2MIN',4X,'P2MAX',
     $6X,'X2MAX',2X,'Y2MAX',3X,'F1(2)')
 1095 FORMAT(5X,'PMIN',7X,'XMIN',3X,'YMIN',5X,'PMAX',7X,'XMAX',3X,
     $'YMAX')
 1200 FORMAT(1X,1PE12.5,1X,I4,6(1X,1PE12.5))
 1300 FORMAT(I2,6(1X,1PE12.5))
 1400 FORMAT(2(1X,I3),8(1X,1PE12.5))
 1500 FORMAT(2(2(1X,1PE12.5,2(1X,F6.2)),1PE12.5))
 1600 FORMAT(2(1X,1PE12.5,2(1X,F6.2)))
C
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C-----------------------------------------------------------------------   
C
C     IF I_HEX2>0:
C     CALCULATION OF SURFACE WEIGHTED FLUXES AND CORNER FLUXES ONLY IN 
C     THE CENTRAL NODE
C  
C     IF I_HEX2=0:            
C     CALCULATION OF SURFACE WEIGHTED FLUXES IN THE CENTRAL AND NEIGHBOU
C     RING NODES FROM THE INCOMING AND OUTGOING PARTIAL CURRENTS
C
C     REMARK:         
C     ONLY FOR THE CASSETTE FULLY SURROUNDED WITH NEIGHBOURING CASSETTES
C     ISYM=360
C     OR SITUATED AT THE INNER BOUNDARY OF THE 30 AND 182 REFLECTIONAL 
C     SYMMETRY OR 60 ROTATIONAL SYMMETRY
C     ISYM=30 (=31 FOR THE CENTRAL CASSETTE)
C             (=32,33,34 FOR THE CASSETTE ON THE INNER BOUNDARY)  
C     ISYM=182
C     ISYM=60 (=61 FOR THE CENTRAL CASSETTE)
C             (=62,63 FOR THE CASSETTE ON THE INNER BOUNDARY) 
C
C     +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
C     +                                                           +
C     + WARNING:                                                  +
C     + THE RECONSTRUCTION IN CASSETTES  SITUATED ON THE REACTOR  +
C     + CORE OUTER BOUNDARY OR IN THE REFLECTOR (IF IT IS ICLUDED +
C     + IN THE REACTOR CORE DESCRIPTION ) IS NOT POSSIBLE !!!     +
C     + THERE ARE NO PARTIAL CURRENTS IN THE NEIGHTBOURING NODES  +  
C     + OUTSIDE THE INVESTIGATED REACTOR VOLUME !!!               +
C     +                                                           +
C     +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
C   
C      INSTEAD OF EQUIVALENCE
C
       ALFA2  => ALFA1
       BETA2  => BETA1
       GAMA2  => GAMA1
       DELTA2 => DELTA1
       P      => P1
C   
C      INSTEAD OF DATA COMMAND
C
      RKSI(1:18) = (/2.,1.,-1.,1.,1.,0.,1.,2.,1.,0.,1.,1.,-1.,1.,2.,1.,
     $               0.,1./)
      JALFA(1:6) = (/30,90,150,0,60,120/)
      EPS = 0.000001
      R1(1:24)  = (/(0.0, I=1,24)/)
      R(1:6)    = (/(0.0, I=1,6)/)
      A1(1:144) = (/(0.0, I=1,144)/)
      A(1:36)   = (/(0.0, I=1,36)/)
      X(1:200)  = (/(0.0, I=1,200)/)
      Y(1:200)  = (/(0.0, I=1,200)/)
      X1(1:200) = (/(0.0, I=1,200)/)
      Y1(1:200) = (/(0.0, I=1,200)/)
      DEGRAD    = 0.01745329251994
C
      NG=2
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
C
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
C     LOCATION OF IMPORTANT VALUES NEEDED FOR FLUX RECONSTRUCTION
C     ***********************************************************
C
C     AF0    ...NODAL FLUX IN THE CENTRAL NODE 0
C     AF     ...NODAL FLUXES IN THE NEIGHBOURING NODES
C     PSI0   ...SURFACE WEIGHTED FLUXES IN THE CENTRAL NODE
C     PCI0   ...CORNER FLUXES IN THE CENTRAL NODE 
C     PSIN   ...SURFACE WEIGHTED FLUXES IN THE NEIGHBOURING NODES
C     PSI    ...SURFACE WEIGHTED FLUXES IN THE NEIGHBOURING NODES
C               USED FOR CALCULATION OF CORNER FLUXES 
C     F      ...NEUTRON FLUXES IN THE HEXAGON CORNERS
C     F01-F03...PARTIAL NEUTRON FLUXES
C
C
C
C                         *                   *
C                       *   *               *   * 
C                     *       *           *       *
C                   *           *       *           *  
C                 *               *   *               *
C               *                   *                   *
C               *                   *                   *
C               *                   *                   *             
C               *        (3)        *PSI(2)  (2)        *
C               *       AF(3)       *       AF(2)       *
C               *                   * F(2)              *
C               *                   * PCI0(2)           *
C             *   *               *   *               *   *  
C           *       * PSI(3)    *       *           *       *
C         *           *       *           *  F03  * PSI(1)    *      
C       *               *   *PSI0(3) PSI0(2)*   * PSIN(3)PSIN(2)*                
C     *             F(3)  *               F01 * F02 F(1)          *
C     *            PCI0(3)*                   * PCI0(1)           *
C     *                   *                   *                   * 
C     *        (4)        *PSI0(4) (0) PSI0(1)*PSIN(4) (1) PSIN(1)*   
C     *       AF(4)       *        AF0        *       AF(1)       *   
C     *             F(4)  *                   * F(6)              *
C     *           PCI0(4) *  PSI0(5) PSI0(6)  * PCI0(6)           *
C       *               *   *               *   * PSIN(5)PSIN(6)* 
C         *    PSI(4) *       *           *       *           *
C           *       *           *       *    PSI(6) *       *
C             *   *               *   *               *   *      
C               *             F(5)  *                   *
C               *            PCI0(5)*                   *
C               *                   *                   *
C               *        (5)  PSI(5)*        (6)        *
C               *       AF(5)       *       AF(6)       *
C               *                   *                   *
C               *                   *                   *
C                 *               *   *               *
C                   *           *       *           *
C                     *       *           *       *
C                       *   *               *   *
C                         *                   *
C
C
C      REMARK TO THIS PICTURE:
C      -NUMBERIG OF ENERGY GROUP IS NEGLECTED
C      -PSIN(1) = PSIN(1,1)
C       PSIN(2) = PSIN(1,2)
C       PSIN(3) = PSIN(1,3)
C       PSIN(4) = PSIN(1,4)
C       PSIN(5) = PSIN(1,5)
C       PSIN(6) = PSIN(1,6)
C            
C
CHDJ2000-1====+====2====+====3====+====4====+====5====+====6====+====7== 
C                
C     CALCULATION OF NEUTRON FLUXES IN THE HEXAGON CORNERS
C 
      D0(1)=DF
      D0(2)=DT
C
CHDJ2000+1====+====2====+====3====+====4====+====5====+====6====+====7==
C
      DO 8 K=1,NG
      DO 7 I=1,5
      IF(I_HEX2.GT.0) THEN
       F(I,K) = PCI0(I,K)
      ELSE
       F01=2*PSI0(I+1,K)/3.+2*PSI0(I,K)/3.-AF0(K)/3.
       F02=2*PSI0(I,K)/3.+2*PSI(I,K)/3.-AF(I,K)/3.
       F03=2*PSI(I,K)/3.+2*PSI0(I+1,K)/3.-AF(I+1,K)/3.
CHDJ2000+
C
C      TEST PRINT
C 
       IF(IPRN2.EQ.1) THEN
         WRITE(LUNREC,1031)K,I
         WRITE(LUNREC,1032)
         WRITE(LUNREC,1000)AF0(K),AF(I,K),AF(I+1,K)
         WRITE(LUNREC,1034)
         WRITE(LUNREC,1000)F01,F02,F03
         WRITE(LUNREC,1035)
         WRITE(LUNREC,1000)D0(K),D(I,K),D(I+1,K)
       ENDIF
CHDJ2000-
C
C      PARTIAL CORNER FLUXES ARE WEIGHTED BY DIFFUSION COEFFICIENTS
C
       F(I,K)=(D0(K)*F01+D(I,K)*F02+D(I+1,K)*F03)/(D0(K)+D(I,K)+D(I+1,K)
     $ )
C
C      ARITHMETICAL WEIGHTING OF PARTIAL CORNER FLUXES
C 
C      F(I,K)=(F01+F02+F03)/3.
C
      ENDIF
    7 CONTINUE
      IF(I_HEX2.GT.0) THEN
       F(6,K) = PCI0(6,K)
      ELSE
       F01=2*PSI0(1,K)/3.+2*PSI0(6,K)/3.-AF0(K)/3.
       F02=2*PSI0(6,K)/3.+2*PSI(6,K)/3.-AF(6,K)/3.
       F03=2*PSI(6,K)/3.+2*PSI0(1,K)/3.-AF(1,K)/3.
CHDJ2000+
C
C      TEST PRINT
C
       IF(IPRN2.EQ.1) THEN
         WRITE(LUNREC,1031)K,I
         WRITE(LUNREC,1033)
         WRITE(LUNREC,1000)AF0(K),AF(6,K),AF(1,K)
         WRITE(LUNREC,1034)
         WRITE(LUNREC,1000)F01,F02,F03
         WRITE(LUNREC,1036)
         WRITE(LUNREC,1000)D0(K),D(6,K),D(1,K)
       ENDIF
CHDJ2000-
C
C      PARTIAL CORNER FLUXES ARE WEIGHTED BY DIFFUSION COEFFICIENTS
C
       F(6,K)=(D0(K)*F01+D(6,K)*F02+D(1,K)*F03)/(D0(K)+D(6,K)+D(1,K))
C
C      ARITHMETICAL WEIGHTING OF PARTIAL CORNER FLUXES
C 
C      F(6,K)=(F01+F02+F03)/3.
C
      ENDIF
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
