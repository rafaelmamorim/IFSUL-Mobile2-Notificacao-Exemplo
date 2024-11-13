package br.com.rafaelamorim.exemplousonotific;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

public class Notificacao {

    private static final String CHANNEL_ID = "ID_CHANNEL_NOTIFICACAO";
    private static final int NOTIFICATION_ID = 123456;
    private NotificationCompat.Builder notificationBuilder;

    public Notificacao() {}

    public void pedePermissao(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                if (context instanceof Activity) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                }
            }
        }
    }

    public void mostraNotificacaoLocal(Context context){
        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        createNotificationChannel(context);

        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentTitle("Minha notificação")
                .setContentText("Esta é a mensagem da notificação")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
        }
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Canal de Notificações", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }
}

