package redstorm.storm.jruby;

import storm.trident.tuple.TridentTuple;
import java.lang.Object;
import storm.trident.operation.CombinerAggregator;

public class JRubyProxyCombinerAggregator implements CombinerAggregator {
  CombinerAggregator _proxy;
  String _realClassName;
  String _baseClassPath;
  String[] _fields;

  public JRubyProxyCombinerAggregator(final String baseClassPath, final String realClassName, final String[] fields) {
    _baseClassPath = baseClassPath;
    _realClassName = realClassName;
    _fields = fields;
  }


  @Override
  public Object init(final TridentTuple _tridentTuple) {
    
    _proxy.init(_tridentTuple);
    
  }

  @Override
  public Object combine(Object _object1, Object _object2) {
    
    _proxy.combine(_object1, _object2);
    
  }

  @Override
  public Object zero() {
    
    _proxy.zero();
    
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
