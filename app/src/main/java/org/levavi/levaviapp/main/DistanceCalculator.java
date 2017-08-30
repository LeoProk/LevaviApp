package org.levavi.levaviapp.main;

import android.location.Location;

import org.levavi.levaviapp.interfaces.FactoryInterface;
import org.levavi.levaviapp.pojos.Geometry;

/**
 * culculate the distance of current location and the location of the item/service
 */

final class DistanceCalculator implements FactoryInterface {
    //list item location lat lang
    private Geometry mGeometry;
    //the current location
    private Location mLocation;

    public DistanceCalculator(Geometry geometry){
        mGeometry = geometry;

    }

    @Override
    public Object doTask() {
        return null;
    }
}
