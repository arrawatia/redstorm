package redstorm.storm.jruby;

import storm.trident.tuple.TridentTuple;
import java.lang.Object;
import storm.trident.operation.CombinerAggregator;

public class JRubyProxyCombinerAggregator implements CombinerAggregator {
  CombinerAggregator _proxy;
  String _realClassName;
  String _baseClassPath;

  public JRubyProxyCombinerAggregator(final String baseClassPath, final String realClassName) {
    _baseClassPath = baseClassPath;
    _realClassName = realClassName;
  }


  @Override
  public Object init(final TridentTuple _tridentTuple) {
      if(_proxy == null) {
            _proxy = newProxy(_baseClassPath, _realClassName);
          }
    return _proxy.init(_tridentTuple);
    
  }

  @Override
  public Object combine(Object _object1, Object _object2) {
     if(_proxy == null) {
           _proxy = newProxy(_baseClassPath, _realClassName);
         }
    return _proxy.combine(_object1, _object2);
    
  }

  @Override
  public Object zero() {
     if(_proxy == null) {
           _proxy = newProxy(_baseClassPath, _realClassName);
         }
    return _proxy.zero();
    
  }


  private static CombinerAggregator newProxy(final String baseClassPath, final String realClassName) {
    try {
      redstorm.proxy.ProxyCombinerAggregator proxy = new redstorm.proxy.ProxyCombinerAggregator(baseClassPath, realClassName);
      return proxy;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
