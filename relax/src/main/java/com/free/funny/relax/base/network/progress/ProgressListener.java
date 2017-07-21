/**
 * Copyright 2015 ZhangQu Li
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.free.funny.relax.base.network.progress;


import com.free.funny.relax.base.network.SimpleCallBack;

import java.io.InputStream;

public abstract class ProgressListener implements SimpleCallBack<InputStream> {

    public abstract void onProgress(long currentBytes, long contentLength, boolean done);

    @Override
    public void onSuccess(InputStream body) {
    }

    @Override
    public void onError(Exception e) {
    }

    @Override
    public void onFailure(Object body) {
    }
}