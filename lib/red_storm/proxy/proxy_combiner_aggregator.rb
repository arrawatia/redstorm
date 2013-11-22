require 'java'


java_import 'storm.trident.tuple.TridentTuple'

java_import 'java.lang.Object'

java_import 'storm.trident.operation.CombinerAggregator'


module Backtype
  java_import 'backtype.storm.Config'
end

java_package 'redstorm.proxy'

class ProxyCombinerAggregator
  java_implements CombinerAggregator

  java_signature 'CombinerAggregator (String base_class_path, String real_class_name)'
  def initialize(base_class_path, real_class_name)
    @real = Object.module_eval(real_class_name).new
  rescue NameError
    require base_class_path
    @real = Object.module_eval(real_class_name).new
  end

  java_signature 'Object init(TridentTuple)'
  def init(_trident_tuple)
    @real.init(_trident_tuple)
  end

  java_signature 'Object combine(Object v1, Object v2)'
  def combine(_object1, _object2)
    @real.combine(_object1, _object2)
  end

  java_signature 'Object zero()'
  def zero()
    @real.zero()
  end


end
