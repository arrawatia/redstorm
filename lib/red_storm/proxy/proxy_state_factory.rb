require 'java'

java_import 'java.util.Map'
java_import 'backtype.storm.task.IMetricsContext'
java_import 'storm.trident.state.State'
java_import 'storm.trident.state.StateFactory'


module Backtype
  java_import 'backtype.storm.Config'
end

java_package 'redstorm.proxy'

class ProxyStateFactory
  java_implements StateFactory

  java_signature 'StateFactory (String base_class_path, String real_class_name)'
  def initialize(base_class_path, real_class_name)
    @real = Object.module_eval(real_class_name).new
  rescue NameError
    require base_class_path
    @real = Object.module_eval(real_class_name).new
  end

  java_signature 'State makeState(Map, IMetricsContext, int, int)'
  def make_state(_map, _context, _partition_index, _num_partition)
    @real.make_state(_map, _context, _partition_index, _num_partition)
  end


end
