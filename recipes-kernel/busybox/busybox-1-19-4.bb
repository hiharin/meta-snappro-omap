
# figure out how to set this up....DWW
LICENSE = "GPLv2+"
LIC_FILE_CHKSUM = "file://LICENSE;md5=de10de48642ab74318e893a61105afbb"

PROVIDES = "virtual/busybox"

SRC_URI[md5sum] = "9c0cae5a0379228e7b55e5b29528df8e"
SRC_URI = "http://www.busybox.net/downloads/busybox-1.19.4.tar.bz2"
SRC_URI += "file://busybox.patch"
