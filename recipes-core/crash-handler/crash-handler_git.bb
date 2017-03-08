SUMMARY = "Tim Bird's experimental crash_handler program."
HOMEPAGE = "http://elinux.org/Crash_handler"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/aaronovz1/crash-handler;branch=master \
"

DEPENDS = "libunwind"
RDEPENDS_${PN} = " libunwind"

S = "${WORKDIR}/git"
SRCREV = "1890f202973517939e5933f3d9cab180457747e3"
PV = "0.7+git${SRCREV}"

do_compile() {
    oe_runmake CC="${CC}"
}

do_install() {
    install -d ${D}${sbindir}
    install -m 0755 ${S}/crash_handler ${D}${sbindir}
}
