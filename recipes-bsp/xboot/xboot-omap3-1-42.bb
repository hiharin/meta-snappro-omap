require xboot.inc

SRCREV = "23bf763690219f0eab18c6dd45161bad79980e07"

PV = "1"
PR = "42"

SRC_URI = "git:///home/dwest/projects/git/xboot"
SRC_URI += "file://0001-HIMAX-lcd-updates-NAND-updates-UART-config-gpio-conf.patch"

S = "${WORKDIR}/git"
#KBRANCH = "master"

do_configure_prepend() {

	if [ "${UBOOT_SERIAL_CONSOLE}" = "ttyS2" ]; then
            sed -i 's:ttyO2:ttyS2:g' ${S}/include/configs/omap3_overo.h
        fi

	if [ "${UBOOT_SERIAL_CONSOLE}" = "ttyO2" ]; then
            sed -i 's:ttyS2:ttyO2:g' ${S}/include/configs/omap3_overo.h
        fi

        if [ "${UBOOT_FAST_MODE}" = "nand" ]; then
            sed -i 's:/\*#define CONFIG_FAST_BOOT\*/:#define CONFIG_FAST_BOOT:g' ${S}/include/configs/omap3_overo.h ${S}/include/configs/omap3_logic.h
            sed -i 's:/\*#define CONFIG_FAST_BOOT_NAND\*/:#define CONFIG_FAST_BOOT_NAND:g' ${S}/include/configs/omap3_overo.h ${S}/include/configs/omap3_logic.h
        fi

        if [ "${UBOOT_FAST_MODE}" = "mmc" ]; then
            sed -i 's:/\*#define CONFIG_FAST_BOOT\*/:#define CONFIG_FAST_BOOT:g' ${S}/include/configs/omap3_overo.h ${S}/include/configs/omap3_logic.h
            sed -i 's:/\*#define CONFIG_FAST_BOOT_MMC\*/:#define CONFIG_FAST_BOOT_MMC:g' ${S}/include/configs/omap3_overo.h ${S}/include/configs/omap3_logic.h
        fi
}
