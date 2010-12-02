      MODULE CND_XS22
C-----------------------------------------------------------------------
C     PURPOSE     : MODULE FOR MULTIGROUP CROSS SECTION LIBRARY
C                   MOX RIA (Purdue University)
C-----------------------------------------------------------------------
C     AUTHOR      : GU                                DATE : 01.06.2005
C-----------------------------------------------------------------------
C     MODIFIED BY : KLI Arrays for poison data		DATE : 06.08.2008
C-----------------------------------------------------------------------
C
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C     LIBRARY CROSS SECTIONS AND PARAMETERS LIBRARIES 22
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
      INTEGER(4) MAXBURN,MAXMESH,MAXDENS,MAXCB,MAXTF,MAXTM,MAXDOSC,
     $           MAXUPSC
C
      INTEGER(4) NXSTYP,KPASS
C
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOBURN
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOXSDENS
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOXSCB
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOXSTF
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOXSTM
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOMESH
C
      INTEGER(4) , DIMENSION(:,:) , ALLOCATABLE ::  IDOMAX
      INTEGER(4) , DIMENSION(:,:) , ALLOCATABLE ::  IUPMAX
C
      INTEGER(4) , DIMENSION(:)   , ALLOCATABLE ::  NODOSC
      INTEGER(4) , DIMENSION(:)   , ALLOCATABLE ::  NOUPSC
C
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  DENS_P
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  CB_P
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  TF_P
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  SQTF_P
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  TM_P
      REAL(8) , DIMENSION(:,:)       , ALLOCATABLE ::  BRN_P
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  SIGTRP
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  SIGABS
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  SIGNF
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  SIGFKP
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  SIGSC
      REAL(8) , DIMENSION(:,:,:,:,:) , ALLOCATABLE ::  SIGSCDO
      REAL(8) , DIMENSION(:,:,:,:,:) , ALLOCATABLE ::  SIGSCUP
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  ADFLIB
C
      REAL(8) , DIMENSION(:,:,:)     , ALLOCATABLE ::  CHIFIS
      REAL(8) , DIMENSION(:,:,:)     , ALLOCATABLE ::  CHIPR
      REAL(8) , DIMENSION(:,:,:)     , ALLOCATABLE ::  VELINV
      REAL(8) , DIMENSION(:,:,:)     , ALLOCATABLE ::  RLAMBDN
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  BETAIDN
      REAL(8) , DIMENSION(:,:,:,:)   , ALLOCATABLE ::  CHIDELN
CKLI+080806
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  GM22IO
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  GM22XE
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  GM22PM
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  CRS22XE1
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  CRS22XE2
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  CRS22SM1
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  CRS22SM2
CKLI-080806
C
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C     INTERMEDIATE CROSS SECTIONS AND PARAMETERS LIBRARY 22
C     FOR INTERPOLATING THE FEEDBACK CROSS SECTIONS OF
C     GIVEN BURNUP IBRN AND TYPE ISET
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
      REAL(8) , DIMENSION(:,:,:)   , ALLOCATABLE ::  ASIG
C      REAL(8) , DIMENSION(:,:)   , ALLOCATABLE ::  ASIGABS
C      REAL(8) , DIMENSION(:,:)   , ALLOCATABLE ::  ASIGNF
C      REAL(8) , DIMENSION(:,:)   , ALLOCATABLE ::  ASIGFKP
C      REAL(8) , DIMENSION(:,:)   , ALLOCATABLE ::  AADFLIB
      REAL(8) , DIMENSION(:,:,:) , ALLOCATABLE ::  ASIGSCDO
      REAL(8) , DIMENSION(:,:,:) , ALLOCATABLE ::  ASIGSCUP
C
      REAL(8) , DIMENSION(:,:)   , ALLOCATABLE ::  SIGMA
      REAL(8) , DIMENSION(:,:) ,   ALLOCATABLE ::  SIGMADO
      REAL(8) , DIMENSION(:,:) ,   ALLOCATABLE ::  SIGMAUP
C
      REAL(8) , DIMENSION(:)       , ALLOCATABLE ::  HSIGSC
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  ACHIFIS
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  ACHIAV
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  AVELINV
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  ARLAMBDN
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  ABETAIDN
      REAL(8) , DIMENSION(:,:)     , ALLOCATABLE ::  ACHIDELN
C
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NODOGR
      INTEGER(4) , DIMENSION(:) , ALLOCATABLE ::  NOUPGR
C
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C     INTERMEDIATE CROSS SECTIONS AND PARAMETERS LIBRARY 22
C===+====1====+====2====+====3====+====4====+====5====+====6====+====7==
C
      INTEGER(4) NXSCORE NXSREFL
C
      CHARACTER(LEN=255) , DIMENSION(:) , ALLOCATABLE :: SETNAMEL
      CHARACTER(LEN=20)  , DIMENSION(:) , ALLOCATABLE :: SETNAMES
C
      END MODULE
