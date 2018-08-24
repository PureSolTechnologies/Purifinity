#include "com_puresoltechnologies_toolshed_jpcap_JPCap.h"
#include <pcap.h>

JNIEXPORT jlong JNICALL Java_com_puresoltechnologies_toolshed_jpcap_JPCap_pcapOpenLive(
		JNIEnv *env, jclass cls, jstring device, jint snaplen, jint promisc,
		jint to_ms) {
	const char* dev = (*env)->GetStringUTFChars(env, device, 0);
	char errbuf[PCAP_ERRBUF_SIZE];
	pcap_t *descr = pcap_open_live(dev, snaplen, promisc, to_ms, errbuf);
	if (!descr) {
		jclass illegalArgumentExceptionCls = (*env)->FindClass(env,
				"com/puresoltechnologies/toolshed/jpcap/JPCapException");
		(*env)->ThrowNew(env, illegalArgumentExceptionCls, errbuf);
		return -1;
	}
	return (long) descr;
}

JNIEXPORT jstring JNICALL Java_com_puresoltechnologies_toolshed_jpcap_JPCap_pcapLookupDev(
		JNIEnv *env, jclass cls) {
	char errbuf[PCAP_ERRBUF_SIZE];
	char *dev = pcap_lookupdev(errbuf);
	if (!dev) {
		jclass illegalArgumentExceptionCls = (*env)->FindClass(env,
				"com/puresoltechnologies/toolshed/jpcap/JPCapException");
		(*env)->ThrowNew(env, illegalArgumentExceptionCls, errbuf);
		return (*env)->NewStringUTF(env, dev);
	}
	return (*env)->NewStringUTF(env, dev);
}

JNIEXPORT void JNICALL Java_com_puresoltechnologies_toolshed_jpcap_JPCap_pcapClose(
		JNIEnv *env, jclass cls, jlong pcapId) {
	pcap_t *desc = (pcap_t*)pcapId;
	pcap_close(desc);
}
