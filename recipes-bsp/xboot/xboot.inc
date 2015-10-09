SECTION = "bootloaders"
PRIORITY = "optional"
#LICENSE = "GPLv2+"
LICENSE = "CLOSED"

#LIC_FILES_CHKSUM = "file://...;md5..."

PROVIDES = "virtual/bootloader"



PACKAGE_ARCH = "${MACHINE_ARCH}"
PARALLEL_MAKE=""

#EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

#TODO, should update the machine variable globally, DWW
LMACHINE = "dm3730logic"

XBOOT_MACHINE ?= "${LMACHINE}_config"
XBOOT_BINARY ?= "xboot.img"
XBOOT_IMAGE ?= "xboot-${LMACHINE}-${PV}-${PR}.img"
XBOOT_SYMLINK ?= "xboot-${LMACHINE}.img"
#XBOOT_MAKE_TARGET ?= "all"
XBOOT_MAKE_TARGET ?= "MLO"

do_configure () {
	oe_runmake ${XBOOT_MACHINE}
}

do_compile () {
	unset LDFLAGS
	unset CFLAGS
	unset CPPFLAGS
	oe_runmake ${XBOOT_MAKE_TARGET}
	oe_runmake tools env HOSTCC="${CC}" HOSTSTRIP="${STRIP}"
}

#do_install () {
#	install -d ${D}/boot
#	install ${S}/${XBOOT_BINARY} ${D}/boot/${XBOOT_IMAGE}
#	ln -sf ${XBOOT_IMAGE} ${D}/boot/${XBOOT_BINARY}
#
#	if [ -e ${WORKDIR}/fw_env.config ] ; then
#	    install -d ${D}${base_sbindir}
#		install -d ${D}${sysconfdir}
#		install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
#		install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
#		install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv
#	fi
#
#}
