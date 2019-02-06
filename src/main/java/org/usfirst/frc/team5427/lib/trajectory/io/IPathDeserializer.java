package org.usfirst.frc.team5427.lib.trajectory.io;

import  org.usfirst.frc.team5427.lib.trajectory.Path;

/**
 * Interface for methods that deserializes a Path or Trajectory.
 * 
 * @author Jared341
 */
public interface IPathDeserializer {
  
  public Path deserialize(String serialized);
}
