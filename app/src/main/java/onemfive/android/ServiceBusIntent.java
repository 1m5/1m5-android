package onemfive.android;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import ra.servicebus.ServiceBus;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceBusIntent extends IntentService {

    private static final String ACTION_START_BUS = "onemfive.android.action.START_BUS";
    private static final String ACTION_STOP_BUS = "onemfive.android.action.STOP_BUS";

    private static final String MAX_MANCON = "onemfive.android.extra.MAX_MANCON";
    private static final String GRACEFUL = "onemfive.android.extra.GRACEFUL";

    private ServiceBus serviceBus;

    public ServiceBusIntent() {
        super("ServiceBusIntent");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startBus(Context context, String maxManCon) {
        Intent intent = new Intent(context, ServiceBusIntent.class);
        intent.setAction(ACTION_START_BUS);
        intent.putExtra(MAX_MANCON, maxManCon);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void stopBus(Context context, String graceful) {
        Intent intent = new Intent(context, ServiceBusIntent.class);
        intent.setAction(ACTION_STOP_BUS);
        intent.putExtra(GRACEFUL, graceful);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_START_BUS.equals(action)) {
                final String maxManCon = intent.getStringExtra(MAX_MANCON);
                handleStartBus(maxManCon);
            } else if (ACTION_STOP_BUS.equals(action)) {
                final String graceful = intent.getStringExtra(GRACEFUL);
                handleStopBus(graceful);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleStartBus(String maxManCon) {
//        super.getApplicationContext().getSharedPreferences()
        serviceBus = new ServiceBus();
//        serviceBus.start(props);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleStopBus(String graceful) {
        if("true".equals(graceful))
            serviceBus.gracefulShutdown();
        else
            serviceBus.shutdown();
    }
}
