require 'java'


java_import 'java.util.List'

java_import 'storm.trident.state.map.IBackingMap'


module Backtype
  java_import 'backtype.storm.Config'
end

java_package 'redstorm.proxy'

class ProxyBackingMap
  java_implements IBackingMap

  java_signature 'IBackingMap (String base_class_path, String real_class_name)'
  def initialize(base_class_path, real_class_name)
    @real = Object.module_eval(real_class_name).new
  rescue NameError
    require base_class_path
    @real = Object.module_eval(real_class_name).new
  end

  java_signature 'List multiGet(List)'
  def multi_get(_keys)
    @real.multi_get(_keys)
  end

  java_signature 'void multiPut(List, List)'
  def multi_put(_keys, _values)
    @real.multi_put(_keys, _values)
  end


end
