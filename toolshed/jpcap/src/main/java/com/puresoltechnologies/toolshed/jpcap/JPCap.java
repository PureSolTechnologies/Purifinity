package com.puresoltechnologies.toolshed.jpcap;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPCap {

    private static final Logger logger = LoggerFactory.getLogger(JPCap.class);
    static {
	logger.info("Initilizing libpcap library mapper...");
	File file = new File("src/main/c/libjpcap.so");
	System.load(file.getAbsolutePath());
	logger.info("libpcap library mapper initialized.");
    }

    // pcap_create(3PCAP)
    // get a pcap_t for live capture
    public static native long pcapOpenLive(String device, int snaplen, int promisc, int readoutTimeoutMillis)
	    throws JPCapException;

    // pcap_activate(3PCAP)
    // activate a pcap_t for live capture
    //
    // pcap_findalldevs(3PCAP)
    // get a list of devices that can be opened for a live capture
    //
    // pcap_freealldevs(3PCAP)
    // free list of devices

    /**
     * pcap_lookupdev(3PCAP): get first non-loopback device on that list
     * 
     * @return
     * @throws IOException
     */
    public static native String pcapLookupDev() throws JPCapException;

    // pcap_open_offline(3PCAP)
    // open a pcap_t for a ``savefile'', given a pathname
    //
    // pcap_open_offline_with_tstamp_precision(3PCAP)
    // open a pcap_t for a ``savefile'', given a pathname, and specify the precision
    // to provide for packet time stamps
    //
    // pcap_fopen_offline(3PCAP)
    // open a pcap_t for a ``savefile'', given a FILE *
    //
    // pcap_fopen_offline_with_tstamp_precision(3PCAP)
    // open a pcap_t for a ``savefile'', given a FILE *, and specify the precision
    // to provide for packet time stamps
    //
    // pcap_open_dead(3PCAP)
    // create a ``fake'' pcap_t

    /**
     * pcap_close(3PCAP): close a pcap_t
     * 
     * @param pcapId
     * @return
     * @throws IOException
     */
    public static native void pcapClose(long pcapId) throws JPCapException;

    // pcap_set_snaplen(3PCAP)
    // set the snapshot length for a not-yet-activated pcap_t for live capture
    //
    // pcap_snapshot(3PCAP)
    // get the snapshot length for a pcap_t
    //
    // pcap_set_promisc(3PCAP)
    // set promiscuous mode for a not-yet-activated pcap_t for live capture
    //
    // pcap_set_rfmon(3PCAP)
    // set monitor mode for a not-yet-activated pcap_t for live capture
    //
    // pcap_can_set_rfmon(3PCAP)
    // determine whether monitor mode can be set for a pcap_t for live capture
    //
    // pcap_set_timeout(3PCAP)
    // set read timeout for a not-yet-activated pcap_t for live capture
    //
    // pcap_set_buffer_size(3PCAP)
    // set buffer size for a not-yet-activated pcap_t for live capture
    //
    // pcap_set_tstamp_type(3PCAP)
    // set time stamp type for a not-yet-activated pcap_t for live capture
    //
    // pcap_list_tstamp_types(3PCAP)
    // get list of available time stamp types for a not-yet-activated pcap_t for
    // live capture
    //
    // pcap_free_tstamp_types(3PCAP)
    // free list of available time stamp types
    //
    // pcap_tstamp_type_val_to_name(3PCAP)
    // get name for a time stamp type
    //
    // pcap_tstamp_type_val_to_description(3PCAP)
    // get description for a time stamp type
    //
    // pcap_tstamp_type_name_to_val(3PCAP)
    // get time stamp type corresponding to a name
    //
    // pcap_set_tstamp_precision(3PCAP)
    // set time stamp precision for a not-yet-activated pcap_t for live capture
    //
    // pcap_get_tstamp_precision(3PCAP)
    // get the time stamp precision of a pcap_t for live capture
    //
    // pcap_datalink(3PCAP)
    // get link-layer header type for a pcap_t
    //
    // pcap_file(3PCAP)
    // get the FILE * for a pcap_t opened for a ``savefile''
    //
    // pcap_is_swapped(3PCAP)
    // determine whether a ``savefile'' being read came from a machine with the
    // opposite byte order
    //
    // pcap_major_version(3PCAP)
    // pcap_minor_version(3PCAP)
    // get the major and minor version of the file format version for a ``savefile''

}
