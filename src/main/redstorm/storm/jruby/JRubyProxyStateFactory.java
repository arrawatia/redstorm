package redstorm.storm.jruby;

import java.util.Map;
import backtype.storm.task.IMetricsContext;
import storm.trident.state.State;
import storm.trident.state.StateFactory;

public class JRubyProxyStateFactory implements StateFactory {
  StateFactory _proxy;
  String _realClassName;
  String _baseClassPath;

  public JRubyProxyStateFactory(final String baseClassPath, final String realClassName) {
    _baseClassPath = baseClassPath;
    _realClassName = realClassName;
  }


  @Override
  public State makeState(final Map _map, final IMetricsContext _iMetricsContext, int _partition_index, int _num_partition) {
    if(_proxy == null) {
       _proxy = newProxy(_baseClassPath, _realClassName);
    }
    return _proxy.makeState(_map, _iMetricsContext, _partition_index, _num_partition);
    
  }


  private static StateFactory newProxy(final String baseClassPath, final String realClassName) {
    try {
      redstorm.proxy.ProxyStateFactory proxy = new redstorm.proxy.ProxyStateFactory(baseClassPath, realClassName);
      return proxy;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
