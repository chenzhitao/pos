// IPrinterService.aidl
package com.sunmi.extprinterservice;

// Declare any non-default types here with import statements  

interface ExtPrinterService {

    int sendRawData(in byte[] cmd);

	int printerInit();

    int getPrinterStatus();

	int lineWrap(in int n);

	int pixelWrap(in int n);

	int flush();

    int tab();

    int printText(in String text);

    int printBarCode(in String code, in int type, in int width, in int height, in int hriPos);

    int printQrCode(in String code, in int modeSize, in int errorlevel);

    int printBitmap(in Bitmap bitmap, in int mode);

    int setHorizontalTab(in int[] k);

    int setAlignMode(in int type);

    int cutPaper(in int m, in int n);

    int setFontZoom(in int hori, in int veri);

    int printColumnsText(in String[] colsTextArr, in int[] colsWidthArr, in int[] colsAlign);
}
