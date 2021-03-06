/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hssf.record.chart;


import static org.apache.poi.hssf.record.TestcaseRecordInputStream.confirmRecordEncoding;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.poi.hssf.record.TestcaseRecordInputStream;
import org.junit.Test;

/**
 * Tests the serialization and deserialization of the DataFormatRecord
 * class works correctly.  Test data taken directly from a real
 * Excel file.
 */
public final class TestDataFormatRecord {
    byte[] data = new byte[] {
        (byte)0xFF,(byte)0xFF,      // point number
        (byte)0x00,(byte)0x00,      // series index
        (byte)0x00,(byte)0x00,      // series number
        (byte)0x00,(byte)0x00       // format flags
    };

    @Test
    public void testLoad() {
        DataFormatRecord record = new DataFormatRecord(TestcaseRecordInputStream.create(0x1006, data));
        assertEquals( (short)0xFFFF, record.getPointNumber());
        assertEquals( 0, record.getSeriesIndex());
        assertEquals( 0, record.getSeriesNumber());
        assertEquals( 0, record.getFormatFlags());
        assertFalse(record.isUseExcel4Colors());

        assertEquals( 12, record.getRecordSize() );
    }

    @SuppressWarnings("squid:S2699")
    @Test
    public void testStore() {
        DataFormatRecord record = new DataFormatRecord();
        record.setPointNumber( (short)0xFFFF );
        record.setSeriesIndex( (short)0 );
        record.setSeriesNumber( (short)0 );
        record.setFormatFlags( (short)0 );
        record.setUseExcel4Colors( false );


        byte [] recordBytes = record.serialize();
        confirmRecordEncoding(DataFormatRecord.sid, data, recordBytes);
    }
}
