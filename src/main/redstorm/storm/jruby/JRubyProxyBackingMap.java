package redstorm.storm.jruby;

import java.util.List;
import storm.trident.state.map.IBackingMap;

public class JRubyProxyBackingMap implements IBackingMap {
  IBackingMap _proxy;
  String _realClassName;
  String _baseClassPath;

  public JRubyProxyBackingMap(final String baseClassPath, final String realClassName) {
    _baseClassPath = baseClassPath;
    _realClassName = realClassName;
  }


  @Override
  public List multiGet( List _keys) {
     if(_proxy == null) {
                _proxy = newProxy(_baseClassPath, _realClassName);
              }
    return _proxy.multiGet(_keys);
    
  }

  @Override
  public void multiPut( List _keys, List _values) {
    if(_proxy == null) {
               _proxy = newProxy(_baseClassPath, _realClassName);
             }
    _proxy.multiPut(_keys, _values);
    
  }


  private static IBackingMap newProxy(final String baseClassPath, final String realClassName) {
    try {
      redstorm.proxy.ProxyBackingMap proxy = new redstorm.proxy.ProxyBackingMap(baseClassPath, realClassName);
      return proxy;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
