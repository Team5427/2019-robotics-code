package org.usfirst.frc.team5427.lib.trajectory.io;

import  org.usfirst.frc.team5427.lib.trajectory.Path;

/**
 * Interface for methods that serialize a Path or Trajectory.
 *
 * @author Jared341
 */
public interface IPathSerializer {

  public String serialize(Path path);
}
