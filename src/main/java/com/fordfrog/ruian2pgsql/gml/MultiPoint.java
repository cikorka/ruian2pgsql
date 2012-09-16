/**
 * Copyright 2012 Miroslav Šulc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.fordfrog.ruian2pgsql.gml;

import java.util.ArrayList;
import java.util.List;

/**
 * MultiPoint.
 *
 * @author fordfrog
 */
public class MultiPoint implements Geometry, GeometryWithPoints {

    /**
     * Points of the multipoint geometry.
     */
    private final List<Point> points = new ArrayList<>(2);
    /**
     * Geometry SRID.
     */
    private Integer srid;

    @Override
    public String toWKT() {
        final StringBuilder sbString = new StringBuilder(100);
        WKTUtils.appendSrid(sbString, srid);

        sbString.append("MULTIPOINT(");
        WKTUtils.appendPoints(sbString, points);
        sbString.append(')');

        return sbString.toString();
    }

    @Override
    public Integer getSrid() {
        return srid;
    }

    /**
     * Setter for {@link #srid}.
     *
     * @param srid {@link #srid}
     */
    public void setSrid(final Integer srid) {
        this.srid = srid;
    }

    @Override
    public void addPoint(final Point point) {
        points.add(point);
    }
}