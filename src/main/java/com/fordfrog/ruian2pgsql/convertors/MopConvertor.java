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
package com.fordfrog.ruian2pgsql.convertors;

import com.fordfrog.ruian2pgsql.containers.Mop;
import com.fordfrog.ruian2pgsql.utils.Namespaces;
import com.fordfrog.ruian2pgsql.utils.PreparedStatementEx;
import com.fordfrog.ruian2pgsql.utils.Utils;
import com.fordfrog.ruian2pgsql.utils.XMLUtils;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Convertor for Mop element.
 *
 * @author fordfrog
 */
public class MopConvertor extends AbstractSaveConvertor<Mop> {

    /**
     * Namespace of the element.
     */
    private static final String NAMESPACE = Namespaces.MOP_INT_TYPY;
    /**
     * SQL statement for checking whether the item already exists.
     */
    private static final String SQL_EXISTS =
            "SELECT 1 FROM rn_mop WHERE kod = ?";
    /**
     * SQL statement for insertion of new item.
     */
    private static final String SQL_INSERT = "INSERT INTO rn_mop "
            + "(nazev, nespravny, obec_kod, id_trans_ruian, plati_od, "
            + "nz_id_globalni, zmena_grafiky, definicni_bod, hranice, kod) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ST_GeomFromGML(?), "
            + "ST_GeomFromGML(?), ?)";
    /**
     * SQL statement for update of existing item.
     */
    private static final String SQL_UPDATE = "UPDATE rn_mop "
            + "SET nazev = ?, nespravny = ?, obec_kod = ?, id_trans_ruian = ?, "
            + "plati_od = ?, nz_id_globalni = ?, zmena_grafiky = ?, "
            + "definicni_bod = ST_GeomFromGML(?), hranice = ST_GeomFromGML(?), "
            + "item_timestamp = timezone('utc', now()), deleted = false "
            + "WHERE kod = ? AND id_trans_ruian < ?";

    /**
     * Creates new instance of MopConvertor.
     *
     * @param con database connection
     *
     * @throws SQLException Thrown if problem occurred while communicating with
     *                      database.
     */
    public MopConvertor(final Connection con) throws SQLException {
        super(Mop.class, Namespaces.VYMENNY_FORMAT_TYPY, "Mop", con, SQL_EXISTS,
                SQL_INSERT, SQL_UPDATE);
    }

    @Override
    protected void fill(final PreparedStatement pstm, final Mop item,
            final boolean update) throws SQLException {
        final PreparedStatementEx pstmEx = new PreparedStatementEx(pstm);
        pstm.setString(1, item.getNazev());
        pstmEx.setBoolean(2, item.getNespravny());
        pstm.setInt(3, item.getObecKod());
        pstm.setLong(4, item.getIdTransRuian());
        pstmEx.setDate(5, item.getPlatiOd());
        pstm.setLong(6, item.getNzIdGlobalni());
        pstmEx.setBoolean(7, item.getZmenaGrafiky());
        pstm.setString(8, item.getDefinicniBod());
        pstm.setString(9, item.getHranice());
        pstm.setInt(10, item.getKod());

        if (update) {
            pstm.setLong(11, item.getIdTransRuian());
        }
    }

    @Override
    protected void fillExists(final PreparedStatement pstm, final Mop item)
            throws SQLException {
        pstm.setInt(1, item.getKod());
    }

    @Override
    protected void processElement(final XMLStreamReader reader,
            final Mop item, final Writer logFile) throws XMLStreamException {
        switch (reader.getNamespaceURI()) {
            case NAMESPACE:
                switch (reader.getLocalName()) {
                    case "Geometrie":
                        Utils.processGeometrie(reader, getConnection(), item,
                                NAMESPACE, logFile);
                        break;
                    case "GlobalniIdNavrhuZmeny":
                        item.setNzIdGlobalni(
                                Long.parseLong(reader.getElementText()));
                        break;
                    case "IdTransakce":
                        item.setIdTransRuian(
                                Long.parseLong(reader.getElementText()));
                        break;
                    case "Kod":
                        item.setKod(
                                Integer.parseInt(reader.getElementText()));
                        break;
                    case "Nazev":
                        item.setNazev(reader.getElementText());
                        break;
                    case "Nespravny":
                        item.setNespravny(
                                Boolean.valueOf(reader.getElementText()));
                        break;
                    case "Obec":
                        item.setObecKod(
                                Utils.getObecKod(reader, NAMESPACE, logFile));
                        break;
                    case "PlatiOd":
                        item.setPlatiOd(
                                Utils.parseTimestamp(reader.getElementText()));
                        break;
                    default:
                        XMLUtils.processUnsupported(reader, logFile);
                }

                break;
            default:
                XMLUtils.processUnsupported(reader, logFile);
        }
    }
}
