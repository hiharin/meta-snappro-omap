SECTION = "bootloaders"
PRIORITY = "optional"
LICENSE = "GPLv2+"

LIC_FILES_CHKSUM = "file://README;md5=fb7a7e60aceaa99c529b6c667dfcf474"

PROVIDES = "virtual/bootloader"



PACKAGE_ARCH = "${MACHINE_ARCH}"
PARALLEL_MAKE=""

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX}"

#TODO, should update the machine variable globally, DWW
LMACHINE = "dm3730logic"

XBOOT_MACHINE ?= "${LMACHINE}_config"
#XBOOT_BINARY ?= "xboot.img"
#XBOOT_IMAGE ?= "xboot-${LMACHINE}-${PV}-${PR}.img"
#XBOOT_SYMLINK ?= "xboot-${LMACHINE}.img"
XBOOT_MAKE_TARGET ?= "all"

do_configure () {
	oe_runmake ${XBOOT_MACHINE}
}

do_compile () {
	unset LDFLAGS
    unset CFLAGS
    unset CPPFLAGS
	oe_runmake ${XBOOT_MAKE_TARGET}
    #    oe_runmake tools env HOSTCC="${CC}" HOSTSTRIP="${STRIP}"
}

do_install () {
    #install -d ${D}/boot
    #	install ${S}/${XBOOT_BINARY} ${D}/boot/${XBOOT_IMAGE}
    #ln -sf ${XBOOT_IMAGE} ${D}/boot/${XBOOT_BINARY}
    install ${S}/MLO ${D}/MLO
    #
#	if [ -e ${WORKDIR}/fw_env.config ] ; then
#	    install -d ${D}${base_sbindir}
#		install -d ${D}${sysconfdir}
#		install -m 644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
#		install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_printenv
#		install -m 755 ${S}/tools/env/fw_printenv ${D}${base_sbindir}/fw_setenv
#	fi
#
}

inherit deploy

addtask deploy before do_package after do_compile

do_deploy () {
#	install -d ${DEPLOY_DIR_IMAGE}
#	install ${S}/${UBOOT_BINARY} ${DEPLOY_DIR_IMAGE}/${UBOOT_IMAGE}

#	cd ${DEPLOY_DIR_IMAGE}
#	rm -f ${UBOOT_SYMLINK}
#	ln -sf ${UBOOT_IMAGE} ${UBOOT_SYMLINK}
#	ln -sf ${UBOOT_IMAGE} ${UBOOT_BINARY}

	if [ -e ${S}/MLO ] ; then
		install ${S}/MLO ${DEPLOY_DIR_IMAGE}/MLO
#		rm -f ${DEPLOY_DIR_IMAGE}/MLO-${MACHINE} ${DEPLOY_DIR_IMAGE}/MLO
#		ln -sf MLO-${MACHINE}-${PV} ${DEPLOY_DIR_IMAGE}/MLO-${MACHINE}
#		ln -sf MLO-${MACHINE}-${PV} ${DEPLOY_DIR_IMAGE}/MLO
	fi
}
