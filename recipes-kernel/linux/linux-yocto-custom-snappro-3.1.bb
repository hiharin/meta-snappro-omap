# This file was derived from the linux-yocto-custom.bb recipe in
# oe-core.
#
# linux-yocto-custom.bb:
#
#   A yocto-bsp-generated kernel recipe that uses the linux-yocto and
#   oe-core kernel classes to apply a subset of yocto kernel
#   management to git managed kernel repositories.
#
# Warning:
#
#   Building this kernel without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition:
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition:
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRCREV = "9bb1282f6a7754955c18be912fbc2b55d133f1b9"
# using local clone of the archive linux for the current build.
SRC_URI = "git:///home/dwest/projects/git/snap-pro-kernel"
# figure out how to use the source repo for the build...
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux-stable.git"
#SRC_URI = "git:///home/dwest/projects/git/snap-pro-kernel;protocol=file;bareclone=1"
SRC_URI += "file://linux-3.1.10_0001-Patch-LogicPD-Removed-nand-support-by-logicpd.patch"
SRC_URI += "file://linux-3.1.10_0002-Patch-IDexx-without-nand-support.patch"
SRC_URI += "file://linux-3.1.10_0003-Nand-Support-Added-Backported-the-MTD-NAND-layers-fr.patch"
SRC_URI += "file://linux-3.1.10_0004-Changed-ECC-to-HW-ECC-added-usblp-module-to-walnut_c.patch"
SRC_URI += "file://linux-3.1.10_0005-Onchip-NAND-ECC-support-added.patch"
SRC_URI += "file://linux-3.1.10_0006-Added-Micron-onchip-NAND-ECC-support-to-walnut_defco.patch"
SRC_URI += "file://linux-3.1.10_0007-Changed-NAND-timings.patch"
SRC_URI += "file://linux-3.1.10_0008-Removed-MCU_COMM-driver-this-driver-is-now-seprate-m.patch"
SRC_URI += "file://linux-3.1.10_0009-Added-Support-for-USB-Serial-Converter-with-FTDI-Sin.patch"
SRC_URI += "file://linux-3.1.10_0010-panel-driver-modified-for-gamma-contrast-correction-in-hx8363.patch"
SRC_URI += "file://linux-3.1.10_0011-Changed-memory-from-221M-to-472M.patch"
SRC_URI += "file://defconfig"

KBRANCH = "working-branch"
CONFIG_LOCALVERSION = "-idexx-snappro"
LINUX_VERSION ?= "3.1"
LINUX_VERSION_EXTENSION ?= "-custom"

SRCREV="${AUTOREV}"
# work around for do_validate_branches error
SRCREV_machine="${SRCREV}"

PR = "r1"
#PV = "${LINUX_VERSION}+git${SRCPV}"
PV = "${LINUX_VERSION}"

COMPATIBLE_MACHINE = "snappro"
COMPATIBLE_MACHINE_snappro = "snappro"
