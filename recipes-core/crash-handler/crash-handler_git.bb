SUMMARY = "Tim Bird's experimental crash_handler program."
HOMEPAGE = "http://elinux.org/Crash_handler"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI = "git://github.com/aaronovz1/crash-handler;branch=master \
           file://configure-crash-handler.service \
"

inherit systemd

DEPENDS = "libunwind"
RDEPENDS_${PN} = " libunwind"

S = "${WORKDIR}/git"
SRCREV = "2ad9a58350d5527e64d4f084d628f4aca8f4c610"
PV = "0.7.2+git${SRCREV}"

FILES_${PN} += "${sbindir}/crash_handler"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
    oe_runmake CC="${CC}"
}

do_install() {
    install -d ${D}${sbindir}
    install -d ${D}${systemd_unitdir}/system

    install -m 0755 ${S}/crash_handler ${D}${sbindir}
    install -m 644 ${WORKDIR}/*.service      ${D}/${systemd_unitdir}/system
}

SYSTEMD_SERVICE_${PN} = "configure-crash-handler.service"
