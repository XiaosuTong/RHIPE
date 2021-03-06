/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.godhuli.rhipe;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;
import java.util.List;


/////////////////////////////////////////////////////////////
// A wrapper class around NLineInputFormat		   //
// Useful for simulations, as opposed to reading to hadoop //
/////////////////////////////////////////////////////////////

public class RNLineInputFormat extends FileInputFormat<RHNumeric, RHText> {
    // To change split length
    // Adjust mapreduce.input.lineinputformat.linespermap
    private static org.apache.hadoop.mapreduce.lib.input.NLineInputFormat _nlf;

    public RNLineInputFormat() {
        _nlf = new org.apache.hadoop.mapreduce.lib.input.NLineInputFormat();
    }

    public RecordReader<RHNumeric, RHText> createRecordReader(final InputSplit genericSplit, final TaskAttemptContext context) throws IOException {
        context.setStatus(genericSplit.toString());
        return new RXLineRecordReader();
    }

    public List<InputSplit> getSplits(final JobContext job) throws IOException {
        return _nlf.getSplits(job);
    }

    public static List<org.apache.hadoop.mapreduce.lib.input.FileSplit> getSplitsForFile(final FileStatus status, final Configuration conf, final int numLinesPerSplit) throws IOException {
        return _nlf.getSplitsForFile(status, conf, numLinesPerSplit);
    }
}
