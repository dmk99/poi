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

package org.apache.poi.hwpf.usermodel;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianConsts;
import org.apache.poi.util.Removal;

/**
 * This class is used to determine line spacing for a paragraph.
 */
public final class LineSpacingDescriptor implements Duplicatable {
  short _dyaLine;
  short _fMultiLinespace;

  public LineSpacingDescriptor() {
      _dyaLine = 240;
      _fMultiLinespace = 1;
  }

  public LineSpacingDescriptor(LineSpacingDescriptor other) {
      _dyaLine = other._dyaLine;
      _fMultiLinespace = other._fMultiLinespace;
  }


  public LineSpacingDescriptor(byte[] buf, int offset) {
    _dyaLine = LittleEndian.getShort(buf, offset);
    _fMultiLinespace = LittleEndian.getShort(buf, offset + LittleEndianConsts.SHORT_SIZE);
  }

  @Override
  @SuppressWarnings("squid:S2975")
  @Deprecated
  @Removal(version = "5.0.0")
  public LineSpacingDescriptor clone() {
    return copy();
  }

  @Override
  public LineSpacingDescriptor copy() {
    return new LineSpacingDescriptor(this);
  }

  public void setMultiLinespace(short fMultiLinespace)
  {
    _fMultiLinespace = fMultiLinespace;
  }

  public int toInt()
  {
    byte[] intHolder = new byte[4];
    serialize(intHolder, 0);
    return LittleEndian.getInt(intHolder);
  }

  public void serialize(byte[] buf, int offset)
  {
    LittleEndian.putShort(buf, offset, _dyaLine);
    LittleEndian.putShort(buf, offset + LittleEndianConsts.SHORT_SIZE, _fMultiLinespace);
  }

  public void setDyaLine(short dyaLine)
  {
    _dyaLine = dyaLine;
  }

  @Override
  public boolean equals(Object o)
  {
    if (!(o instanceof LineSpacingDescriptor)) return false;
    LineSpacingDescriptor lspd = (LineSpacingDescriptor)o;

    return _dyaLine == lspd._dyaLine && _fMultiLinespace == lspd._fMultiLinespace;
  }

  @Override
  public int hashCode() {
      assert false : "hashCode not designed";
      return 42; // any arbitrary constant will do
  }

    public boolean isEmpty()
    {
        return _dyaLine == 0 && _fMultiLinespace == 0;
    }

    @Override
    public String toString()
    {
        if ( isEmpty() )
            return "[LSPD] EMPTY";

        return "[LSPD] (dyaLine: " + _dyaLine + "; fMultLinespace: "
                + _fMultiLinespace + ")";
    }
}
