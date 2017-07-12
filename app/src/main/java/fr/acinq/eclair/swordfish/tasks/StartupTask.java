package fr.acinq.eclair.swordfish.tasks;

import android.content.Context;
import android.os.AsyncTask;

import fr.acinq.eclair.swordfish.EclairHelper;
import fr.acinq.eclair.swordfish.EclairStartException;

public class StartupTask extends AsyncTask<String, Integer, EclairHelper> {

  private AsyncSetupResponse delegate;
  private Context context;
  public StartupTask(AsyncSetupResponse delegate, Context context) {
    this.delegate = delegate;
    this.context = context;
  }

  @Override
  protected EclairHelper doInBackground(String... params) {
    try {
      return new EclairHelper(context);
    } catch (EclairStartException e) {
      return null;
    }
  }

  @Override
  protected void onPostExecute(EclairHelper instance) {
    delegate.processFinish(instance);
  }

  public interface AsyncSetupResponse {
    void processFinish(EclairHelper instance);
  }

}