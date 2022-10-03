package com.shageldi.handheld.honey;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Map;


/**
 * HoneywellScannerPlugin
 */
public class HoneywellScannerPlugin implements ScannerCallBack
{
    private static final String _METHOD_CHANNEL = "honeywellscanner";
    private static final String _IS_SUPPORTED = "isSupported";
    private static final String _IS_STARTED = "isStarted";
    private static final String _SET_PROPERTIES = "setProperties";
    private static final String _START_SCANNER = "startScanner";
    private static final String _RESUME_SCANNER = "resumeScanner";
    private static final String _PAUSE_SCANNER = "pauseScanner";
    private static final String _STOP_SCANNER = "stopScanner";
    private static final String _SOFTWARE_TRIGGER = "softwareTrigger";
    private static final String _START_SCANNING = "startScanning";
    private static final String _STOP_SCANNING = "stopScanning";
    private static final String _ON_DECODED = "onDecoded";
    private static final String _ON_ERROR = "onError";

    private final Handler handler;
    private HoneywellScanner scanner;

    public HoneywellScannerPlugin(Context context) {
        handler = new Handler();
        init(context);
    }

    public void init(Context context) {
        (scanner = new HoneywellScannerNative(context)).setScannerCallBack(this);
    }





    /**
     * Called when decoder has successfully decoded the code
     * <br>
     * Note that this method always called on a worker thread
     *
     * @param scannedData Encapsulates the result of decoding a barcode within an image
     */
    @Override
    public void onDecoded(final ScannedData scannedData)
    {
        handler.post(() -> Log.e(_ON_DECODED,scannedData.toMap().toString()));
    }

    /**
     * Called when error has occurred
     * <br>
     * Note that this method always called on a worker thread
     *
     * @param error Exception that has been thrown
     */
    @Override
    public void onError(final Exception error)
    {
        handler.post(() -> Log.e(_ON_ERROR, error.getMessage()));
    }
}